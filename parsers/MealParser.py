def getMeal(mealname):
  import re, urllib2, os
  from bs4 import BeautifulSoup
  response = urllib2.urlopen('http://menus.tufts.edu/foodpro/shortmenu.asp?sName=TUFTS+DINING&locationNum=11&locationName=Dewick-MacPhie+Dining+Center&naFlag=1')
  soup = BeautifulSoup(response, 'html.parser')
  for elem in soup(text=re.compile(mealname)):
    text_menu = elem.findParents('table')[1].get_text().rstrip()
    text_menu = os.linesep.join([s for s in text_menu.splitlines() if s.strip()])
    print text_menu

getMeal('Breakfast')    
getMeal('Lunch')
getMeal('Dinner')