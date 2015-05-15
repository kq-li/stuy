#! /usr/bin/python

import cgi
import re

__import__('cgitb').enable()

def isInt(s):
    try:
        int(s)
        return True
    except:
        return False

def parseRow(row):
    if '"' in row:
        openQuote = 0
        closeQuote = 0
        for i in range(row.count('"') / 2):
            openQuote = row.find('"')
            closeQuote = row[(openQuote + 1):].find('"')
            row = row[:openQuote] + row[openQuote:closeQuote].replace(',', ';') + row[closeQuote:]
    row = re.split(' *, *', row)
    row = row[:2] + [int(i) if isInt(i) else 's' for i in row[2:]]
    row.append((sum(row[3:]) if isInt(row[3]) else 's'))
    return row

rows = re.split('\r?\n', open('../data/SAT-2010.csv', 'r').read())[:-1]
columnHeaders = re.split(' *, *', rows[0]) + ['Total Mean']
data = [parseRow(row) for row in rows[1:]]

def getCol(column):
    return [row[column] for row in data]
   
def extremeScores(column, count, is_top):
    if column in [3, 4, 5, 6]:
        scores = []
        print '<h1>SAT Query Results</h1>'
        print '<table border=1><tr style=\'font-weight: bold\'><td>' + columnHeaders[column] + '</td><td>School</td></tr>'
        for name, score in zip(getCol(columnHeaders.index('School Name')), getCol(column)):
            if isInt(score):
                scores.append((name.replace(';', ','), score))
        extremes = sorted(scores, key = lambda x: x[1], reverse = is_top)[:count]
        for i in extremes:
            print '<tr><td>' + str(i[0]) + '</td><td>' + str(i[1]) + '</td></tr>'
        print '</table><br>'

HTML_HEADER = 'Content-Type: text/html\n\n'

def show():
  elements = cgi.FieldStorage()
  print HTML_HEADER
  print '<center>'
  if elements.has_key('column') and elements.has_key('count'):
      is_top = True if elements.has_key('is_top') else False
      extremeScores(int(elements.getvalue('column')), int(elements.getvalue('count')), is_top)
  else:
      print 'Please fill out all required fields!'
  print '<a href=\'../flexQuery.html\'>Back</a>'
  print '</center>'

show()
