def getJoeySchedule(location):
  import re, urllib2, os
  from bs4 import BeautifulSoup
  #get the right day and go to the right page!!
  response = urllib2.urlopen('http://publicsafety.tufts.edu/adminsvc/day-schedule-monday-friday/')

  #sat and sunday have same hours??...
  #response = urllib2.urlopen('http://publicsafety.tufts.edu/adminsvc/2015/05/18/tufts-shuttles-have-ceased-operations-for-the-summer/')
  #response = urllib2.urlopen('http://publicsafety.tufts.edu/adminsvc/2015/05/18/tufts-shuttles-have-ceased-operations-for-the-summer/')
  soup = BeautifulSoup(response, 'html.parser')

  
  nonFridayData = []
  friData = []
  satSunData = []
  foundFriday = False
  for elem in soup(text=re.compile(location)):
    table = elem.findParents('table')[0]
  table_body = table.find('tbody')

  rows = table_body.find_all('tr')
  for row in rows:
      cols = row.find_all('td')
      cols = [ele.text.strip() for ele in cols]
      cols = [word.replace(u'\xa0', ' ') for word in cols] #replaces all '\xa0'
      for col in cols:
        if col == 'FRIDAY NIGHTS ONLY':
          foundFriday = True
      if foundFriday:
        cols = [word.replace(u'FRIDAY NIGHTS ONLY', ' ') for word in cols]
        friData.append([col for col in cols])
      else:
        nonFridayData.append([col for col in cols])
  print nonFridayData
  print '\n\n\n\n\n'
  print friData








'''
for count, col in enumerate(cols):
        col = col.replace(u'\xa0', ' ')
        if len(cols) > 0 and cols[count] == 'FRIDAY NIGHTS ONLY':
          foundFriday = True
        if foundFriday and cols[count] != 'FRIDAY NIGHTS ONLY':
          friData.append([col for col in cols])
        else:
        #monToThursData.append([ele for ele in cols if ele]) # Get rid of empty values
          monToThursData.append([col for col in cols])
'''




  
      #for td in cols:
        #text = td.find(text=True) + ';'
        #print text
'''  
  for elem in soup(text=re.compile(location)):
    text_menu = elem.findParents('table')[0]
    text_menu = os.linesep.join([s for s in text_menu.splitlines() if s.strip()])
    print text_menu + '\n'

  table = soup.find('table', attrs={'class':'mceVisualAid'})
  #assert table_body is not NoneType
  #table_body = table.find('tbody')
    
'''

getJoeySchedule('Campus Center')    
#getJoeySchedule('Davis Sq')
#getJoeySchedule('TAB')
