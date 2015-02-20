from BeautifulSoup import BeautifulSoup
import sys
import re

class Antrag:
  def __init__(self):
    self.template = u"""<doc><add>
    <field name="ID">{ID}</field>
    <field name="title">{title}</field>
    <field name="date">{date}</field>
    <field name="area">{area}</field>
    <field name="regelwerk">{regelwerk}</field>
    <field name="result">{result}</field>
    <field name="yes">{yes}</field>
    <field name="no">{no}</field>
    <field name="abstention">{abstention}</field>
    <field name="organ">{organ}</field>
    <field name="competinginitiatives">{competinginitiatives}</field>
    <field name="text">{text}</field>
    <field name="bb">{bb}</field>
    </add></doc>"""
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
  
  def fromHTML(self,fn):
    soup = BeautifulSoup(open(fn).read())
    ID=fn[:-5]
    self.title=''.join(soup.find(attrs={'class':"initiative_head"}).div.text.split(':')[1:]).strip()
    self.html=soup.find(attrs={'class':"draft_content wiki"})
    self.text=self.html.text
    self.bb = unicode(self.html).replace('<','[').replace('>',']').replace('&nbsp;',' ').replace('&','&amp;')
    self.date=soup.find(attrs={'class':"content issue_policy_info"}).text.split('vor')[1].strip()
    self.area=soup.find(attrs={'class':"area_name"}).text
    self.unit=soup.find(attrs={'class':"unit_name"}).text    
    self.ID='lqfb_'+self.unit+fn[:-5]
    self.regelwerk=soup.find(attrs={'class':"issue_id"}).text.split('#')[0]    
    self.result=soup.find(attrs={'class':"initiative selected"}).div.div.span.img['src'].split('/')[-1]
    self.yes, self.no, self.abstention =soup.find(attrs={'class':"initiative selected"}).find(attrs={'class':"bargraph"})['title'].split(' / ')
    self.organ='LQFB'
    self.competinginitiatives=' '.join([ci.find(attrs={'class':"name"}).a.text.split(':')[0][1:] for ci in  soup.findAll(attrs={'class':"initiative"})])
    

if __name__ == '__main__':
  fn = sys.argv[1]
  a = Antrag()
  a.fromHTML(fn)
  a.toSolr()

