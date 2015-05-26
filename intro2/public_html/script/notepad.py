#! /usr/bin/python

import cgi
import hashlib
import random
import re
import sys

is_local = True
if not is_local:
  __import__('cgitb').enable()

directory = '../notes/'
alphanum = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
noteNames = []

def genName():
  name = ''
  for i in range(6):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def newNote(s):
  name = genName()
  while name in noteNames:
    name = genName()
  noteNames.append(name)
  filename = directory + name
  f = open(filename, 'w')
  f.write(s)
  f.close()
  print 'Note ' + name + ' created!<br>'

def getNote(name):
  filename = directory + name
  lines = re.split('<br>|\n', open(filename, 'r').read())[:-1]
  for line in lines:
    print '<p>' + line + '</p>'


def main():
  if is_local:
#    if len(sys.argv) == 3:
#      print 'Usage: ./notepad.py [r|w] [filename|text]'
#      exit(1)
    print 'Content-Type: text/html\n\n'
    print '<html><head><title>Notepad</title></head><body>'
    mode = sys.argv[1]
    if mode == 'r':
      filename = sys.argv[2]
      getNote(filename)
    elif mode == 'w':
      text = sys.argv[2]
      newNote(text)
  else:
    print 'Content-Type: text/html\n\n'
    print '<html><head><title>Notepad</title></head><body>'    
    elements = cgi.FieldStorage()
    if elements.has_key('read'):
      getNote(elements.getvalue('filename'))
    elif elements.has_key('write'):
      newNote(elements.getvalue('text'))

  print '</body></html>'

main()
