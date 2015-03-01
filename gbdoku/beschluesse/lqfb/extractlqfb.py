# -*- coding: utf-8 -*-

from BeautifulSoup import BeautifulSoup
import sys
import re
import HTMLParser

class YNError(Exception):
    pass

class ResultError(Exception):
    pass

class Antrag:
  def __init__(self):
    self.template = u"""<add><doc>
    <field name="id">{ID}</field>
    <field name="title">{title}</field>
    <field name="date">{date}</field>
    <field name="unit">{unit}</field>
    <field name="area">{area}</field>
    <field name="regelwerk">{regelwerk}</field>
    <field name="result">{result}</field>
    <field name="yes">{yes}</field>
    <field name="no">{no}</field>
    <field name="yespercentage">{yespercentage}</field>
    <field name="nopercentage">{nopercentage}</field>
    <field name="abstention">{abstention}</field>
    <field name="organ">{organ}</field>
    <field name="competinginitiatives">{competinginitiatives}</field>
    <field name="text">{text}</field>
    <field name="word">{words}</field>
    <field name="bb">{bb}</field>
    </doc></add>"""
    pass
   
      
  def toSolr(self):
    out = open('solr/%s'%self.ID,'w')
    out.write(self.template.format(**self.__dict__).encode('utf8'))
    out.close
    
    #print self.title
    #print self.ID
    #print self.result
    #print self.yes, self.no, self.abstention
    #print self.competinginitiatives
    
    
  def toMediaWiki(self):
    pass
  
  def sanitize(self,s):
    return HTMLParser.HTMLParser().unescape(s).replace('','').replace('&Yuml;',u'Ÿ').replace('&euro;',u'€').replace('&oelig;',u'œ').replace('&bdquo;',u'„').replace('&','&amp;').replace('<','&lt;').replace('>','&gt;')
    
    
  def fromHTML(self,fn):
    #self.organ='LQFB'
    self.organ='SMV'
    soup = BeautifulSoup(open(fn).read()) 
    ID=fn.split('/')[-1][:-5] 
    #self.title=self.sanitize(''.join(soup.find(attrs={'class':"initiative_head"}).div.text.split(':')[1:]).strip())
    self.title=self.sanitize(''.join(soup.find(attrs={'class':"initiative"}).text.split(':')[1:]).strip())
    #self.html=soup.find(attrs={'class':"draft_content wiki"})
    self.html=soup.find(attrs={'class':"draft"})
    self.text=self.sanitize(self.html.text)     
    self.bb = self.sanitize(unicode(self.html).replace('<','[').replace('>',']'))
    #self.date=soup.find(attrs={'class':"content issue_policy_info"}).text.split('vor')[1].strip()
    self.date = None
    #self.area=soup.find(attrs={'class':"area_name"}).text
    self.area=soup.find(attrs={'class':"area"}).text
    #self.unit=soup.find(attrs={'class':"unit_name"}).text  
    self.unit=soup.find(attrs={'class':"unit"}).text.replace('Piratenpartei Deutschland ','')
    self.ID=self.organ+''+self.unit+ID
    #print self.ID
    #self.regelwerk=self.sanitize(soup.find(attrs={'class':"issue_id"}).text.split('#')[0])  
    self.regelwerk=self.sanitize(soup.find(attrs={'class':"issue"}).text.split('#')[0])   
	 
    #try:
	#result=soup.find(attrs={'class':"initiative selected"}).div.div.span.img['src'].split('/')[-1]
    #except AttributeError:
	#self.yes=0
	#self.no=0
	#self.abstention=0
	#self.yespercentage=0
	#self.nopercentage=0
	#self.result = 'nicht abgestimmt'
	#result = False
	


    #if result=='award_star_gold_2.png':
	#self.result='angenommen'    
    #if result=='award_star_silver_2.png':
	#self.result='unterlegen'
    #if result=='cross.png':
	#self.result='abgelehnt'  
	

    self.result = 'nicht abgestimmt'
    try:
	result=soup.find(attrs={'class':"sectionRow admitted_info"}).h1.text
	self.result='angenommen'
    except AttributeError: 
	result=soup.find(attrs={'class':"sectionRow not_admitted_info"}).h1.text
	if result=="Abgelehnt":
	    self.result='abgelehnt'
	elif result == 'Initiative nicht zugelassen':
	    self.result='nicht abgestimmt'
	elif 'Platz' in result:
	    self.result='unterlegen'
	else:
	    raise ValueError
    if self.result == 'nicht abgestimmt':
	self.yes = 0
	self.no = 0
	self.abstention = 0
	self.yespercentage = 0
	self.nopercentage = 0
    else:
	self.yes = int(soup.find(attrs={'class':"yes"}).td.text)
	self.no = int(soup.find(attrs={'class':"no"}).td.text)
	self.abstention = int(soup.find(attrs={'class':"abstention"}).td.text)
	
	try:
	    self.yespercentage = 100*self.yes/(self.yes+self.no+0.0)
	except ZeroDivisionError:
	    print "ZDE in ", fn
	    self.yespercentage = 0 
	try:
	    self.nopercentage = 100*self.no/(self.yes+self.no+0.0)
	except ZeroDivisionError:
	    print "ZDE in ", fn
	    self.nopercentage = 0
    
    #self.competinginitiatives=' '.join([ci.find(attrs={'class':"name"}).a.text.split(':')[0][1:] for ci in  soup.findAll(attrs={'class':"initiative"})])  
    self.competinginitiatives=' '.join([sp.a.text.split(':')[0][1:] for sp in  soup.findAll(attrs={'class':"initiative_name"})])   
    
    self.words = ' '.join(list(set(self.text.split())))
    
    #try:
	#if result:
	    #self.yes, self.abstention, self.no =soup.find(attrs={'class':"initiative selected"}).find(attrs={'class':"bargraph"})['title'].split(' / ')
	    #self.yes = int(self.yes)
	    #self.no = int(self.no)
	    #try:
		#self.yespercentage = 100*self.yes/(self.yes+self.no+0.0)
	    #except ZeroDivisionError:
		#print "ZDE in ", fn
		#self.yespercentage = 0 
	    #try:
		#self.nopercentage = 100*self.no/(self.yes+self.no+0.0)
	    #except ZeroDivisionError:
		#print "ZDE in ", fn
		#self.nopercentage = 0
    #except TypeError:
	#self.yes=0
	#self.no=0
	#self.abstention=0
	#self.yespercentage=0
	#self.nopercentage=0
	#self.result = 'nicht abgestimmt'
    
if __name__ == '__main__':
  fn = sys.argv[1]
  a = Antrag()
  try:
    a.fromHTML(fn)
    a.toSolr()
  except YNError:
    print "no result for %s (yn)"%fn 
  except ResultError:
    print "no result for %s (span)"%fn 

