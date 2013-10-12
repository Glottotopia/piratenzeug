#!/usr/bin/python
# -*- coding: utf8 -*-
 
import urllib2
import tempfile  
from xml.etree  import ElementTree as ET 
from htmlentitydefs import name2codepoint

class ALLRis:  
    wikitemplate = """ 
    {| class="wikitable sortable" border="1"
    |+ Sortable table
    |-
    ! bezirk || dsnr || titel || href || partei || typ
    %s
    |-
    |}
    """

    wikirowtemplate="""|-
    | {} || {} || {} || {} || {} || {}"""

    csvtemplate = """	bezirk	dsnr	titel	href	partei	typ 
    %s
    """

    csvrowtemplate="""{}	{}	{}	{}	{}	{}	"""

    def getantraege(self,bezirk,at):  
	"""Retrieve the Bezirk's HTML page for the Antragsteller and extract the data""" 
	
	url = 'http://www.berlin.de/ba-%s/bvv-online/vo040.asp?ATLFDNRM=%s&showall=true' % (self.conformspelling(bezirk.name),at)
	page = urllib2.urlopen(url).read().decode('latin-1') 
	s = self.sanitize(page, bezirk)
	return self.extractantraege(s)    
	    
    def extractantraege(self,scrapedpage):
	"""extract the Antrag data from an ALLRis page"""
	
	parser = ET.XMLParser()
	parser.parser.UseForeignDTD(True)
	parser.entity.update((x, unichr(i)) for x, i in name2codepoint.iteritems())
	
	tf = tempfile.NamedTemporaryFile(delete=False)
	name = tf.name  
	tf.write(scrapedpage.encode('ascii', 'xmlcharrefreplace'))
	tf.close()  
	
	tree = ET.parse(name, parser=parser)   	    
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
	    href = tds[1].find('{http://www.w3.org/1999/xhtml}a').attrib['href']
	    partei = tds[3].text
	    typ = tds[5].text
	    antrag = (bezirk.kuerzel.encode('utf8'), 
		    dsnr.encode('utf8'),
		    title.encode('utf8'),
		    href, 
		    partei.encode('utf8'), 
		    typ.encode('utf8'))
	    antraege.append(antrag)
	return antraege
	
	
    def sanitize(self,f,bezirk): 
	"""
	repair invalid XHTML served by ALLRis
	
	ALLRis pages show a number of recurrent errors. These are repaired in this method
	* one META tag is not properly closed 
	* a path within a comment has slashes
	* one ampersand is not propely escaped
	* a number of closing divs are missing. The precise number varies.
	"""
	opendivs = f.count('<div')
	closeddivs = f.count('</div>')
	offset = opendivs-closeddivs-1 
	a = f.strip().split('\n') 
	a[0] = a[0].replace('<?xml version="1.0"','<?xml version="1.0" encoding="utf8" ')  
	a = [l.replace('&showall=true','&amp;showall=true') 
	    for l in a 
	    if '<meta name="ROBOTS" content="INDEX, NOFOLLOW">' not in l
	    and ' den Pfad /bvv-online/ aufgerufen werden' not in l
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
	
    def write(self,s, format='csv'):
	"""output Antrag data in tabular form"""
	
	if format=='wiki':
	    out = self.wikitemplate% '\n'.join([self.wikirowtemplate.format(*a) for a in s]) 
	if format=='csv': 
	    out = self.csvtemplate% '\n'.join([self.csvrowtemplate.format(*a) for a in s])  
	print out
     
    
class Bezirk:
    def __init__(self,name,kuerzel,ats):
	self.name = name
	self.kuerzel = kuerzel
	self.ats = ats

class Land:
    """We use the Land class for Berlin, even if other Laender do not have Bezirke as immediate constituents """    
    
    bezirke =  [
	Bezirk('Friedrichshain-Kreuzberg',
		'FK', 
		dict(PIRATEN=(88,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)
	),
	Bezirk(u'Tempelhof-Schöneberg',
		'TS',
		dict(PIRATEN=(132,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk(u'Treptow-Köpenick',
		'TK',
		dict(PIRATEN=(141,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Steglitz-Zehlendorf',
		'FK',
		dict(PIRATEN=(95,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Spandau',
		'SP',
		dict(PIRATEN=(164,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Reinickendorf',
		'RD',
		dict(PIRATEN=(127,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Pankow',
		'Pa',
		dict(PIRATEN=(164,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk(u'Neukölln',
		'NK',
		dict(PIRATEN=(71,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Mitte',
		'FK',
		dict(PIRATEN=(40,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Marzahn-Hellersdorf',
		'MH',
		dict(PIRATEN=(69,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Charlottenburg-Wilmersdorf',
		'CW',
		dict(PIRATEN=(267,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),
	Bezirk('Lichtenberg',
		'LI',
		dict(PIRATEN=(104,),
		    SPD=(-1,),
		    CDU=(-1,),
		    Gruene=(-1,),
		    Linke=(-1,),
		    NPD=(-1,),
	)),	
	] 
	
 
if __name__ == '__main__':
    store = []
    berlin = Land()
    allris = ALLRis()
    for bezirk in berlin.bezirke: 
	for at in bezirk.ats['PIRATEN']: 
	    store += allris.getantraege(bezirk,at) 
    allris.write(store, format="csv")	
	


    