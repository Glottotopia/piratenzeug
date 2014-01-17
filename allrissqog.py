#!/usr/bin/python
# -*- coding: utf8 -*-

import urllib as urllib1
import urllib2
import tempfile  
import pickle
import re
from xml.etree  import ElementTree as ET 
from htmlentitydefs import name2codepoint

class ALLRis:  
    wikitemplate = u""" 
    {| class="wikitable sortable" border="1"
    |+ Sortable table
    |-
    ! bezirk || dsnr || titel || url || parteien || typ
    %s
    |-
    |}
    """

    wikirowtemplate=u"""|-
    | {kuerzel} || {dsnr} || {titel} || {url} || {parteien} || {typ}"""

    csvtemplate = u"""	bezirk	dsnr	titel	url	partei	typ
 
    %s
    """

    csvrowtemplate=u"""{bezirk}	{dsnr}	{title}	{url}	{partei}	{typ}	"""
    
    solrtemplate  = u"""<add><doc>
  <field name="id">{ID}</field>  
  <field name="typ">{typ}</field>  
  <field name="titel">{title}</field>  
  <field name="bezirk">{bezirk.name}</field>  
  {parteifields} 
  {wordfields}
  {ausschussfields}
  <field name="text_de">{text}</field> 
  <field name="status">{status}</field>
  {locationfield}
  <field name="url">{url}</field>  
  <field name="#words">{lenws}</field>  
  <field name="#chars">{lenchars}</field>  
  <field name="parteien">{lenparties}</field>   
</doc></add>
""" 

    baseurl = "http://www.berlin.de"
    
    locationstore = {}
    
    def getantraege(self,bezirk,atlfdnr,parteien):  
	"""Retrieve the Bezirk's HTML page for the Antragsteller and extract the data""" 
	
	url = 'http://www.berlin.de/ba-%s/bvv-online/vo040.asp?ATLFDNRM=%s&showall=true' % (self.conformspelling(bezirk.name),atlfdnr)
	print url
	page = urllib2.urlopen(url).read().decode('latin-1') 
	s = self.sanitize(page, bezirk)
	return self.extractantraege(s,parteien)    
	    
    def extractantraege(self,scrapedpage,parteien):
	"""extract the Antrag data from an ALLRis page"""
	
	parser = ET.XMLParser()
	parser.parser.UseForeignDTD(True)
	parser.entity.update((x, unichr(i)) for x, i in name2codepoint.iteritems())
	
	tf = tempfile.NamedTemporaryFile(delete=False)
	name = tf.name  
	tf.write(scrapedpage.encode('ascii', 'xmlcharrefreplace'))
	tf.close()  
	
	try:
	    tree = ET.parse(name, parser=parser)   	    
	except ET.ParseError:
	    print "parse error in", name, "for", parteien
	    return []
	root = tree.getroot()  
	zl11 = root.findall(".//{http://www.w3.org/1999/xhtml}tr[@class='zl11']")
	zl12 = root.findall(".//{http://www.w3.org/1999/xhtml}tr[@class='zl12']")    
	trs = zl11 + zl12
	antraege = []
	for tr in trs: 
	    tds = tr.findall('{http://www.w3.org/1999/xhtml}td') 
	    words = tds[1].find('{http://www.w3.org/1999/xhtml}a').text.split() 
	    dsnr = words[0]
	    title = ' '.join(words[1:]) 
	    href = self.baseurl+tds[1].find('{http://www.w3.org/1999/xhtml}a').attrib['href']
	    #partei = tds[3].text
	    typ = tds[5].text
	    antrag = Antrag(bezirk , dsnr, url=href, parteien=parteien)
	    antrag.title = unicode(title)  
	    antrag.typ = unicode(typ)  
	    antrag.html = self.getAntragHTML(href,bezirk)
	    antrag.text = self.getAntragText(antrag.html)
	    antrag.status = self.getStatus(antrag.html)
	    antrag.ausschuss = self.getAusschussFields(antrag.html)
	    antrag.updateLengths()
	    antraege.append(antrag)
	return antraege
	
    def getStatus(self, html):
	if "Der Antrag wird abgelehnt" in html:
	    return "abgelehnt"
	if u"Der Änderungsantrag wird abgelehnt" in html:
	    return "abgelehnt"
	if "in der BVV abgelehnt" in html:
	    return "abgelehnt"
	if u"ohne Änderungen in der BVV beschlossen" in html:
	    return "angenommen"
	    
	    
    def getAusschussFields(self, html):
	ausschuesse =  list(set(re.findall(u"Ausschuss für ([A-Za-zÄÖÜäöüß]+)", html)))
	return "\n".join(['<field name="ausschuss">%s</field>'%x for x in ausschuesse])
	
    def getAntragHTML(self,url,bezirk):
	scrapedpage = self.sanitize(urllib2.urlopen(url).read().decode('latin-1'),bezirk)
	return scrapedpage
	
    def getAntragText(self,html):
	parser = ET.XMLParser()
	parser.parser.UseForeignDTD(True)
	parser.entity.update((x, unichr(i)) for x, i in name2codepoint.iteritems())
	
	#the parser can only read from a file, not from a string, so we create a file
	tf = tempfile.NamedTemporaryFile(delete=False)
	name = tf.name  
	tf.write(html.encode('ascii', 'xmlcharrefreplace'))
	tf.close()  
	try:
	    tree = ET.parse(name, parser=parser) 
	    root = tree.getroot()  
	    bodys = root.findall('.//{http://www.w3.org/1999/xhtml}body')
	    text = '\n'.join([a for a in bodys[1].itertext()]) 
	    #print '.',
	except ET.ParseError:
	    chunk = html.split('<meta name="generator" content="Aspose.Words for .NET')[1]
	    text = re.sub('<[^>]*?>','',chunk)
	    #print ''
	return text
	
    def sanitize(self,f,bezirk): 
	"""
	repair invalid XHTML served by ALLRis
	
	ALLRis pages show a number of recurrent errors. These are repaired in this method
	* one META tag is not properly closed 
	* a path within a comment has slashes
	* one ampersand is not propely escaped
	* a number of closing divs are missing. The precise number varies.
	* extra XML declarations inline
	* duplicate attributes
	"""
	opendivs = f.count('<div')
	closeddivs = f.count('</div>')
	offset = opendivs-closeddivs-1 
	a = f.strip().split('\n')  
	#add encoding
	a[0] = a[0].replace('<?xml version="1.0"','<?xml version="1.0" encoding="utf8" ')
	a = [l.replace('&showall=true','&amp;showall=true')\
		.replace('<?xml version="1.0" encoding="utf-8" standalone="no"?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">','')\
		.replace('<?xml version="1.0" encoding="iso-8859-1" standalone="no"?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">','')\
		.replace('<hr>','<hr />')\
		.replace('target="_blank"','')
		.replace('bvv-online/images/link_pdoc.gif" alt="PDF-Dokument">','bvv-online/images/link_pdoc.gif" alt="PDF-Dokument" />')		          
	    for l in a 
	    if '<meta name="ROBOTS" content="INDEX, NOFOLLOW">' not in l
	    and ' den Pfad /bvv-online/ aufgerufen werden' not in l
	    and '<!--@set var="std-layout" val="land" --' not in l
	    ]
	a = a[:-2]  
	if bezirk.name in ('Friedrichshain-Kreuzberg', u'Treptow-Köpenick' ):
	    offset = 2
	divs = '\n'+  offset * '</div>'  
	result = '\n'.join(a)+divs+'\n</body>\n	</html>'.encode('utf8') 
	return result
	
    def conformspelling(self,b):
	"""Return the Bezirk's name in the way as it is used in the URL"""
	
	return b.lower().replace(u'ö', 'oe')
	
    def getWords(self,txt): 
	stopwords=[ x for x in """ wird 
bezirksamt 
werden 
bezirksverordnetenversammlung 
eine 
sich 
nicht 
beschließen 
dass 
möge 
sind 
auch 
berlin 
durch 
einer 
nach 
begründung 
soll 
oder 
ersucht 
diese 
über 
kann 
bezirk 
einen 
wurde 
ausschuss 
sitzung 
sowie 
ihrer 
bereits 
haben 
dieser 
sollen 
unter 
können 
dafür 
alle 
beschlossen 
damit 
einem 
wenn 
dazu 
stellen 
antrag 
eines 
gebeten 
keine 
welche 
einzusetzen 
rahmen 
dies 
beauftragt 
hier 
sein 
beschließt 
berichten 
diesem 
dabei 
bürger 
noch 
beschluss 
wurden 
drucksache 
möglichkeit 
zuständigen 
berliner 
möglich 
dieses 
anderen 
bezirks 
fraktion 
gibt 
insbesondere 
zwischen 
bezirksbürgermeister 
erledigt 
ihre 
mehr 
muss 
aufgefordert 
erhalten  """.split()]
	return set([x for x in re.split(u'[^a-zäöüß]',txt.lower()) if len(x)>3 and x not in stopwords])	
	
    def write(self,s, format='csv'):
	"""output Antrag data in tabular form"""
	
	if format=='wiki':
	    out = self.wikitemplate% u'\n'.join([self.wikirowtemplate.format(**a.__dict__) for a in s]) 
	    print out
	if format=='csv':    
	    out = self.csvtemplate % ('\n'.join([self.csvrowtemplate.format(**a.__dict__) for a in s]))
	    print out
	if format=='solr':     
	    for a in s:
		path = './bvvsolr/%s_%s.xml' % (a.bezirk.kuerzel,a.dsnr.replace('/','-'))
		#print path 
		out = open(path, 'w')
		try:
		    a.status = a.status.replace(' ','_')
		except AttributeError:
		    a.status = ''
		out = open(path, 'w')
		try:
		    a.typ = a.typ.replace(' ','_')
		except AttributeError:
		    a.typ = ''
		try:
		    a.url = a.url.replace('&','&amp;')
		except AttributeError:
		    a.url = ''
		a.bezirk.name = a.bezirk.name.replace('-','_') 
		a.updateLengths()
		a.getLocation()
		#print a.parteien
		if a.typ in ('Beschluss','Beschlussempfehlung', 'Dringlichkeitsantrag', 'Antrag_zur_Beschlussfassung', u"Änderungsantrag", u"Entschließung", "Dringlichkeitsbeschlussempfehlung", u"Entschließungsantrag", "Gemeinsamer_Antrag", "Gemeinsamer_Dringlichkeitsantrag", u"Drucksache_zurückgezogen", "Beschluss"):
		    a.typ = 'Antrag'
		a.parteifields = '\n'.join(['<field name="partei">%s</field>'%partei for partei in a.parteien])
		a.wordfields = '\n'.join(['<field name="word">%s</field>'%word for word in self.getWords(a.text)])
		a.status = self.getStatus(a.html)
		a.ausschussfields = self.getAusschussFields(a.html)
		a.text = a.text.replace('& ','&amp;')\
				    .replace('<','&lt;')\
				    .replace('>','&gt;')\
				    .replace('&uuml;',u'ü')\
				    .replace('&ouml;',u'ü')\
				    .replace('&auml;',u'ü')\
				    .replace('&Uuml;',u'Ü')\
				    .replace('&Ouml;',u'Ö')\
				    .replace('&Auml;',u'Ä')\
				    .replace('&szlig;',u'ß')\
				    .replace('&nbsp;','')\
				    .replace('&copy;','(c)')
		d = a.__dict__	
		if d['location'] == None or d['location'].strip() in ('52.5166,13.3833',''):
		    d['locationfield'] = ''
		else:
		    d['locationfield'] = '<field name="location">%s</field>' % d['location']
		d.update({'lenparties':len(a.parteien)}) 
		t = self.solrtemplate.format(**d)
		out.write(t.encode('utf8'))
		out.close() 
	if format == 'pickle': 
	    pickle.dump( s, open( "allris.pkl", "wb" ) )
	 
	
    #def fetch(self,store):
	#"""fetch all antraege"""
	#for a in store:
	    #url = 'http://berlin.de%s' %a.href
	    #print url
	    #page = urllib2.urlopen(url).read().decode('latin-1') 
	    #out = open('texts/%s-%s' % (a.bezirk.kuerzel,a.dsnr.replace('/','_')),'w')
	    #out.write(page.encode('latin-1')) 
	#print out
     
  
class Antrag:
    def __init__(self, bezirk, dsnr, 
                  status='', 
                  text='', 
                  html='', 
                  parteien=("PIRATEN",),
                  typ=None, 
                  location = None, #'52.5166,13.3833'
                  titel = '',
                  url = ''):
	self.bezirk = bezirk	
	self.dsnr = dsnr
	self.status = status
	self.text = text 
	self.html = html 
	self.titel = titel
	self.parteien = parteien
	self.typ = typ 
	self.location = location
	self.url = url
	self.ID = "%s_%s" % (self.bezirk.kuerzel, self.dsnr) 
	self.getLocation()
	
    def updateLengths(self):	    
	self.lenchars = len(self.text)
	self.lenws = len(self.text.split())
	self.lenws = len(self.parteien)
	
    def getLocation(self): 	
	def queryNominatim(p): 
	    via, number = p
	    coords = None
	    try:
		coords = self.bezirk.geodict[via]
	    except KeyError: 
		urlstring = u'http://nominatim.openstreetmap.org/search/de/berlin/{}/{}?format=xml'.format(self.bezirk.name.replace(u'_',u'-').replace(u'ö',u'%C3%B6'),urllib1.quote(via.encode('utf8'))) 
		if number != '':  
		    urlstring = u'http://nominatim.openstreetmap.org/search/de/berlin/{}/{}/{}?format=xml'.format(self.bezirk.name.replace(u'_',u'-').replace(u'ö',u'%C3%B6'),urllib1.quote(via.encode('utf8')),number)  
		info = urllib2.urlopen(urlstring).read()
		root = ET.fromstring(info)
		place = root.find('.//place')
		try:
		    longitude = place.attrib['lon']
		    latitude = place.attrib['lat']
		    #print urlstring, longitude, latitude
		except AttributeError:
		    #print via, number,
		    print urlstring, "noresults"	
		    self.bezirk.geodict[via] = ''
		    return ''
		coords = '%s,%s'%(latitude,longitude)
	    self.bezirk.geodict[via] = coords
	    return coords
	    
	nonstrasse = [u"Einbahnstraße","Einkaufsstraße",u"Fahrradstraße", u"Hauptverkehrsstraße", "Spielplatz", "Schulplatz", u"Hauptstraße", "Der Platz", "Den Platz", u"Die Straße", u"Der Straße", "Serviceplatz", "Stellplatz", "Arbeitsplatz", "Sportplatz", "Parkplatz"]
	
	viastring = u"(Straße|Strasse|Platz|Brücke|Allee)" 
	kleinvia = re.findall(u"([A-ZÖÜÄ][a-zäöüß]+%s) *([0-9]*)"%viastring.lower(),self.text)	    
	grossvia = re.findall(u"([A-ZÖÜÄ][a-zäöüß]+ +%s) *([0-9]*)"%viastring,self.text)
	strichvia =  re.findall(u"([A-ZÖÜÄ][a-zäöüß-]+%s) *([0-9]*)"%viastring,self.text)
	vias = kleinvia + grossvia + strichvia
	places = []   
	places = [(x[0],x[2]) for x in vias]   
	d = {}
	for p in places:
	    v = p[0].strip()
	    n = p[1].strip()
	    if v not in nonstrasse:
		if v.endswith('spielplatz') or v.endswith('arbeitsplatz') or v.endswith('parkplatz') or v.endswith('sportplatz'):
		    continue
		try:
		    d[p] += 1
		except KeyError:
		    d[p] = 1
	#max(d.iterkeys(), key=(lambda key: d[key])) 
	maxvalue = 0
	candidates = []
	for k in d:
	    if d[k]>maxvalue:
		maxvalue = d[k]
		candidates = [k]
		continue
	    if d[k] == maxvalue:
		candidates.append(k) 
	coords = map(queryNominatim, candidates) 
	
	coords = [x for x in coords if x != None]
	if len(coords) >0: 
	    self.location = coords[0]
    
class Bezirk:
    def __init__(self,name,kuerzel,atlfdnrs):
	self.name = name
	self.kuerzel = kuerzel
	self.atlfdnrs = atlfdnrs
	self.geodict = {}

class Land:
    """We use the Land class for Berlin, even if other Laender do not have Bezirke as immediate constituents """    
    
    bezirke =  [
	Bezirk('Friedrichshain-Kreuzberg',
		'FK', 
		{
		'88':('Piraten',),   
		'92':('Piraten','Gruene') ,  
		'128':('Piraten','SPD','Gruene','Linke')   ,
		'89':('Piraten','SPD','CDU','Gruene','Linke')   ,
		'98':('Piraten','SPD','CDU','Gruene','Linke')   , 
		'96':('Piraten','SPD','Gruene',)   ,
		'117':('Piraten','Linke',)     
		} 
	),
	Bezirk(u'Tempelhof-Schöneberg',
		'TS',
		{
		'132':('Piraten',),
		'138':('Piraten',),
		'151':('Piraten','Linke'),
		'166':('Piraten','SPD','CDU','Gruene','Linke'),
		'167':('Piraten','SPD','Gruene','Linke'),
		'140':('Piraten',),
		'155':('Piraten','CDU'),
		'158':('Piraten','CDU','Gruene'),
		'153':('Piraten','Gruene',),
		'165':('Piraten','SPD','CDU','Gruene'),
		'164':('Piraten','SPD','CDU','Gruene','Linke'),
		'148':('Piraten','SPD','Gruene'),
		'142':('Piraten','CDU','Gruene',),
		'159':('Piraten','CDU',),
		'160':('Piraten','CDU','Gruene','Linke',),
		'150':('Piraten','CDU','SPD','Gruene','Linke'),
		'136':('Piraten','SPD','Gruene'),
		'144':('Piraten','SPD','Gruene','Linke'),
		'169':('Piraten','SPD','Gruene','Linke'),
		'146':('Piraten',),
		} 
		),
	Bezirk(u'Treptow-Köpenick',
		'TK',
		{
		'141':('Piraten',),
		'161':('Piraten','Gruene',),
		'175':('Piraten','Gruene','Linke',),
		'166':('Piraten','Linke',),
		'171':('Piraten','Gruene','SPD','Linke'),
		'170':('Piraten','SPD','Linke'),
		'164':('Piraten','Gruene',),
		'163':('Piraten','Linke',),
		'173':('Piraten','SPD','CDU','Gruene'),
		'174':('Piraten','SPD','Gruene','Linke'),
		'165':('Piraten','SPD','CDU','Linke'),
		'167':('Piraten','SPD'), 
		} 
		),
	Bezirk('Steglitz-Zehlendorf',
		'SZ',
		{
		'95':('Piraten',),
		'102':('Piraten','CDU',),
		'107':('Piraten','CDU','Gruene',),
		'117':('Piraten','SPD','CDU',),
		'93':('Piraten','SPD','CDU','Gruene',),
		'120':('Piraten','SPD','CDU','Gruene',),
		'121':('Piraten','Gruene',),
		'105':('Piraten','SPD','CDU','Gruene',),
		'97':('Piraten','SPD','Gruene',),
		'99':('Piraten','SPD','CDU','Gruene',),
		'115':('Piraten','CDU',),
		'109':('Piraten','Gruene',),
		'108':('Piraten','SPD',),
		'110':('Piraten','CDU','Gruene',),
		'113':('Piraten','SPD','CDU','Gruene',),
		'96':('Piraten','SPD',),
		'106':('Piraten','SPD','CDU',),
		'118':('Piraten','SPD','CDU',),
		'119':('Piraten','SPD','CDU',),
		'101':('Piraten','SPD','CDU','Gruene',),
		'116':('Piraten','SPD','CDU','Gruene',),
		'103':('Piraten','SPD','Gruene',),
		'98':('Piraten','SPD','CDU','Gruene',),
		'111':('Piraten','SPD','Gruene',), 
		} 
		),
	Bezirk('Spandau',
		'SP',
		{
		'164':('Piraten',),
		'178':('Piraten','CDU','Gruene',),
		'166':('Piraten','CDU',),
		'173':('Piraten','CDU','SPD','Gruene',),
		'165':('Piraten','CDU','SPD','Gruene',),
		'167':('Piraten','CDU','SPD',),
		'174':('Piraten','Gruene',),
		'176':('Piraten','Gruene',),
		'219':('Piraten','CDU',),
		'218':('Piraten','CDU','SPD',),
		'246':('Piraten','SPD',),
		'208':('Piraten','SPD','Gruene',),
		'224':('Piraten','SPD','Gruene',),
		'248':('Piraten','CDU','Gruene',),
		'220':('Piraten','CDU','SPD','Gruene',),
		'169':('Piraten','SPD','Gruene',),
		'170':('Piraten','SPD','Gruene',),
		'168':('Piraten','SPD',),
		'171':('Piraten','SPD',),
		'209':('Piraten','SPD','Gruene',), 
		} 
		),
	Bezirk('Reinickendorf',
		'RD',
		{
		'127':('Piraten',),
		'167':('Piraten','Gruene','CDU',),
		'122':('Piraten','Gruene','CDU','SPD',),
		'159':('Piraten','Gruene','CDU',),
		'158':('Piraten','SPD',),
		'168':('Piraten','Gruene','SPD',),
		'131':('Piraten','SPD',), 
		} 
		),
	Bezirk('Pankow',
		'Pa',
		{
		'164':('Piraten',),
		'223':('Piraten',),
		'226':('Piraten','Gruene',),
		'235':('Piraten',),
		'238':('Piraten','SPD','Gruene',),
		'225':('Piraten','Gruene',),
		'192':('Piraten','SPD','Gruene','Linke',),
		'195':('Piraten','SPD','Gruene','Linke',),
		'193':('Piraten','SPD',),
		'242':('Piraten','SPD','CDU','Gruene','Linke',),
		'194':('Piraten','SPD','CDU','Gruene',),
		'219':('Piraten','SPD','CDU','Linke',),
		'222':('Piraten','SPD','CDU','Gruene','Linke',),
		'220':('Piraten','Gruene','Linke',),
		'186':('Piraten','SPD','Gruene','Linke',),
		'224':('Piraten','SPD','Gruene',),
		'167':('Piraten','SPD','CDU','Gruene','Linke',),
		'237':('Piraten','SPD','Gruene','Linke',),
		'185':('Piraten','Linke',),
		'227':('Piraten','Linke',),
		'228':('Piraten','SPD','Gruene','Linke',),
		'199':('Piraten','SPD','Gruene','Linke',),
		'203':('Piraten','SPD','Gruene','Linke',),
		'206':('Piraten','SPD','CDU','Gruene','Linke',),
		'204':('Piraten','Linke',),
		'236':('Piraten','Gruene',),
		'205':('Piraten','SPD','Gruene',), 
		} 
		),
	Bezirk(u'Neukölln',
		'NK',
		{
		'71':('Piraten',),
		'82':('Piraten','Linke','Gruene','CDU'),
		'84':('Piraten','Gruene',),
		'70':('Piraten','Linke','Gruene',),
		'80':('Piraten','Linke','Gruene','SPD',),
		'81':('Piraten','Linke','Gruene','SPD',),
		'86':('Piraten','SPD',),
		'87':('Piraten','Linke','SPD',), 
		}  
		),  
	Bezirk('Mitte',
		'MI',
		{
		'40':('Piraten',), 
		}  
		),
	Bezirk('Marzahn-Hellersdorf',
		'MH',
		{
		'69':('Piraten',),
		'81':('Piraten','Linke'),
		}  
		),
	Bezirk('Charlottenburg-Wilmersdorf',
		'CW',
		{
		'267':('Piraten',),
		'300':('Piraten','CDU',),
		'321':('Piraten','CDU','Linke',),
		'304':('Piraten','CDU','SPD',),
		'290':('Piraten','SPD','CDU','Gruene',),
		'291':('Piraten','SPD','CDU','Gruene','Linke',),
		'334':('Piraten','CDU','Gruene',),
		'306':('Piraten','CDU','Gruene','Linke',),
		'301':('Piraten','SPD','CDU','Gruene',),
		'283':('Piraten','SPD','CDU','Gruene','Linke',),
		'289':('Piraten','Gruene','Linke',),
		'293':('Piraten','Gruene',),
		'307':('Piraten','Gruene','Linke',),
		'320':('Piraten','SPD','Gruene',),
		'314':('Piraten','SPD','CDU','Gruene',),
		'310':('Piraten','SPD','Gruene','Linke',),
		'302':('Piraten','SPD','Gruene','Linke',),
		'288':('Piraten','SPD','CDU','Gruene','Linke',),
		'281':('Piraten','SPD','Gruene','Linke',),
		'275':('Piraten','Linke',),
		'277':('Piraten','Gruene','Linke',),
		'326':('Piraten','Gruene','Linke',),
		'282':('Piraten','SPD','Gruene','Linke',),
		'317':('Piraten','CDU',),
		'308':('Piraten','Gruene',),
		'318':('Piraten','Gruene','Linke',),
		'327':('Piraten','SPD','Gruene','Linke',),
		'331':('Piraten','SPD','CDU','Gruene','Linke',),
		'319':('Piraten','Linke',),
		'325':('Piraten','Linke',),
		'328':('Piraten','SPD','Gruene',),
		'330':('Piraten','SPD','CDU','Gruene',),
		'266':('Piraten','SPD','CDU','Gruene','Linke',),
		'270':('Piraten','SPD','CDU','Gruene','Linke',),
		'332':('Piraten','SPD','CDU','Gruene',),
		'265':('Piraten','SPD','CDU','Gruene'),
		'305':('Piraten','SPD','CDU','Gruene','Linke',),
		'324':('Piraten','SPD','CDU','Gruene','Linke',),
		'313':('Piraten','SPD','Gruene',),
		'296':('Piraten','SPD','Gruene','Linke',),
		'272':('Piraten','SPD','CDU','Gruene','Linke',),
		'309':('Piraten','SPD','Gruene',),
		'323':('Piraten','SPD','Linke',),
		'292':('Piraten','SPD','Gruene','Linke',),
		'271':('Piraten','SPD','CDU','Gruene','Linke',), 
		} 
		),
	Bezirk('Lichtenberg',
		'LI',
		{
		'104':('Piraten',),
		'106':('Piraten','Linke',),
		'102':('Piraten','Gruene','CDU','SPD',),
		'105':('Piraten','Linke','SPD',), 
		}  
		)
	] 
	
 
if __name__ == '__main__':
    store = []
    try:
	berlin = pickle.load( open( "berlin.pkl", "rb" ) )
    except IOError:
	berlin = Land()
    allris = ALLRis()
    #online = True	
    online = False	 
    if online:
	for i,bezirk in enumerate(berlin.bezirke): 
	    #if bezirk.kuerzel!='FK':
		#continue
	    print bezirk.kuerzel, bezirk.geodict
	    for atlfdnr in bezirk.atlfdnrs:  
		print  atlfdnr,bezirk.atlfdnrs[atlfdnr]  
		store += allris.getantraege(bezirk,atlfdnr,bezirk.atlfdnrs[atlfdnr])  
	    print bezirk.geodict
	    print ''
	    berlin.bezirke[i] = bezirk
	pickle.dump(berlin, open( "berlin.pkl", "wb" ) ) 
	pickle.dump(store, open( "allris.pkl", "wb" ) ) 
    else:
	store = pickle.load( open( "allris.pkl", "rb" ) )
	print "writing"
    allris.write(store, format="solr")	
	


    