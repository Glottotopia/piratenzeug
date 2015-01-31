# -*- coding: utf-8 -*-

template = r"""
{{{{Beschlussantrag_BE
|Typ = Vorstandssitzung
|Datum = {year}-{month}-{day}
|Nummer = {year}-{month}-{day}/{ID} 
|Titel = {title}
|Text = {text}
|Begründung = {begruendung}
|LiquidFeedback = 
|Antragsteller = {antragsteller}
|Dafür = {dafuer}
|Dagegen = {dagegen}
|Enthaltung = {enthaltung}
|Ergebnis = {result}
|Vertagt = 
|Umsetzungsverantwortlich = {uv}
|Link = {link}
|Redebedarf = 
}}}}
"""

fn = "2014.csv"
year = 2014


for i,l in enumerate(open(fn).read().split('\n2014\t')[1:]):
  print i
  print l
  month, day, ID, title, text, begruendung, antragsteller, dafuer, dagegen, enthaltung, result, uv = l.split('\t')
  if len(ID)==1:
    ID = "0%s"%ID 
  if len(month)==1:
    month = "0%s"%month
  if len(day)==1:
    day = "0%s"%day
  date = "%s-%s-%s"%(year, month, day)
  link = "BE:Beschlussantrag Vorstandssitzung/%s/%s" % (date, ID) 
  out = open("%s%%2F%s"%(date,ID), "w")
  out.write(template.format(**locals()))
  out.close()