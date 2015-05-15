#! /usr/bin/python

import cgi
import re
import hashlib
import random

__import__('cgitb').enable()

directory = '../notes/'
alphanum = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
noteNames = []

def genName():
  name = ''
  for i in range(6):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def newNote():
  name = genName()
  while name in noteNames:
    name = genName()
  noteNames.append(name)
  filename = directory + name
  s = raw_input('Editing note ' + name + ':\n')
  f = open(filename, 'w')
  f.write(s)
  f.close()

  
newNote()
