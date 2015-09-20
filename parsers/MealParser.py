<<<<<<< HEAD
from flask import Flask

app = Flask(__name__)

import re, urllib2, os, json, datetime
from bs4 import BeautifulSoup

port = int(os.environ.get('PORT', 5000))

def getMeal(date,dining_hall):
  url = 'http://menus.tufts.edu/foodpro/shortmenu.asp?sName=TUFTS+DINING&locationNum=11&locationName='+dining_hall+'&dtdate='+date.strftime('%m/%d/%y')
  print url
  response = urllib2.urlopen(url)
  soup = BeautifulSoup(response, 'html.parser')
  json = '[{'
  meals = ['Breakfast','Lunch','Dinner']
  for meal_name in meals:
    for elem in soup(text=re.compile(meal_name)):
      text_menu = elem.findParents('table')[1].get_text()
      text_menu = os.linesep.join([s for s in text_menu.splitlines() if s.strip()])
      text_menu = text_menu.replace('"','\\"')
      name_array = []
      name_array = re.findall("(?s)(?<=\n--\s)(.+?)(?=\s--\n)",text_menu)
      text_array = re.findall("(?s)(?<=--\n)(.+?)(?=\n--|$)",text_menu)
      json += '\"meal\" : { \"meal-name\" : \"'+meal_name+'\", \"menu\" : {'
      for idx,each in enumerate(name_array):
        json += '\"menu-section\" : { \"category\": \"'+each+'\", \"items\" : \"'+text_array[idx].replace('\n',',')+'\" },'
      json = json[:-1]
      json+='}},'
  json = json[:-1]
  json+='} ]'
  return json
    
    
    
@app.route("/dining-hall/dewick")
def hello():
    today = datetime.date.today()
    return getMeal(today,'Dewick-MacPhie+Dining+Center')
    
@app.route("/dining-hall/hodgdon")
def hello():
    today = datetime.date.today()
    return getMeal(today,'Hodgdon+Good-+To-+Go+Take-+Out')
    
@app.route("/dining-hall/carm")
def hello():
    today = datetime.date.today()
    return getMeal(today,'Carmichael+Dining+Center')

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=port)