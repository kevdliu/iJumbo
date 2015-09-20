def getEvents():
  import requests, re#urllib2, os, re
  from bs4 import BeautifulSoup
  #response = urllib2.urlopen('http://www.trumba.com/calendars/tufts.rss')

  request = requests.get('http://www.trumba.com/calendars/tufts.rss')
  soup = BeautifulSoup(request.text)
  items = soup.find_all('item')
  for item in items:
    title = item.find('title').text
    link = item.find('link').text
    description = item.find('description').get_text()

    #descParts = soup.find_all('<br')
    #description = description.get_text()

    time = description.split('<br', 1)
    time = time[0].split('&nbsp', 1)

    print title + '\n'
    when = time[0]
    print 'WHEN: ' + when + '\n'
    building = re.findall('(?<=&#39;detailBase&#39;\)\">)(.*)(?=<\/a>.*<br\/><b>Campus<\/b>)', description)
    #building = item.find('description').find(re.compile('(?<=&#39;detailBase&#39;)\">)(.*)(?=<\/a> <br\/><b>)'))
    
    
    print 'BUILDING: '
    print building

    campus = re.findall('(?<=<\/a> <br\/><b>Campus<\/b>:&nbsp;)(.*)(?= <br\/><b>City)', description)
    print 'CAMPUS: '
    print campus

    city = re.findall('(?<= \<br\/\>\<b\>City\<\/b\>:&nbsp;)(.*[A-Z].*)(?= <br\/><b>Location Details<\/b>:)', description)
    print 'CITY: '
    print city

    locDets = re.findall('(?<= <br\/><b>Location Details<\/b>:&nbsp;)(.*)(?=<br\/><b>Category)', description)
    print 'LOCATION DETAILS: '
    print locDets

    category = re.findall('(?<= <br\/><b>Category<\/b>:&nbsp;)(.*)(?=<br\/><b>School)', description)
    print 'CATEGORY: '
    print category

    #school = re.findall('(?<= <br\/><b>School<\/b>:&nbsp;)(.*)(?=<br\/><b>Event Sponsor)', description)
    #print 'SCHOOL: '
    #print school

    admission = re.findall('(?<= <br\/><b>Admission<\/b>:&nbsp;)(.*)(?=<br\/><b>)', description)
    print 'ADMISSION: '
    print admission

    moreInfo = re.findall('(?<=<br\/><b>More info<\/b>:&nbsp;<a href=")(.*)(?=" target)', description)
    print 'MORE INFO: '
    print moreInfo

    description = re.findall('(?<=<br\/><br\/>)(.*)(?= <br\/><br\/><b>)', description)

    if len(description) > 0:
      description[0] = re.sub("<\/?\w+\s+[^>]*>", ' ', description[0])
      description[0] = re.sub("</a>", ' ', description[0])
      #description[0] = re.sub("&#39", '', description[0])
      #description[0] = re.sub("&amp", '', description[0])
      print 'DESCRIPTION: '
      print description
    
    print '***\n'
    
getEvents()
