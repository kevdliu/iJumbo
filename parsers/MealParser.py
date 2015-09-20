import re, urllib2, os, json
from bs4 import BeautifulSoup

def getMeal(mealname):
  response = urllib2.urlopen('http://menus.tufts.edu/foodpro/shortmenu.asp?sName=TUFTS+DINING&locationNum=11&locationName=Dewick-MacPhie+Dining+Center&naFlag=1')
  soup = BeautifulSoup(response, 'html.parser')
  for elem in soup(text=re.compile(mealname)):
    text_menu = elem.findParents('table')[1].get_text()
    text_menu = os.linesep.join([s for s in text_menu.splitlines() if s.strip()])
    name_array = []
    name_array = re.findall("(?s)(?<=\n--\s)(.+?)(?=\s--\n)",text_menu)
    text_array = re.findall("(?s)(?<=--\n)(.+?)(?=\n--|$)",text_menu)
    print name_array
    print text_array
    json = '['
    for idx,each in enumerate(name_array):
      json += '{ category: '+each+', items : '+text_array[idx].replace('\n',',')+' },'
    json+=']'
    print json

getMeal('Breakfast')    
getMeal('Lunch')
getMeal('Dinner')