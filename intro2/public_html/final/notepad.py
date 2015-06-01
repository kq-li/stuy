#! /usr/bin/python

import cgi
import hashlib
import os
import random
import re
import sys

__import__('cgitb').enable()

directory = 'notes/'
alphanum = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
noteNames = os.listdir(directory)
users = dict([tuple(line.split(' ')) for line in open('users', 'r').read().split('\n')[:-1]])

def genName(n):
  name = ''
  for i in range(n):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def header():
  print 'Content-Type: text/html\n\n'
  print '<html><head>'
  print '<title>PasteNote BinPad</title>'
  print '<link rel=stylesheet type=text/css href=notepad.css>'
  print '</head><body>'

def footer():
  print '</body></html>'

def auth(username, password):
  if username in users and users[username] == password:
    print 'Successfully logged in!<br>'
  else:
    print 'Incorrect username/password.<br>'
  print '<a href=notepad.py>Back to Main</a>'

def login():

  print '''
<h1>Login</h1>
<form action=notepad.py method=post>
  Username:&nbsp;
  <input type=text name=user placeholder=Username><br>
  Password:&nbsp;
  <input type=password name=pass placeholder=Password><br>
  <input type=submit name=login value=Login><br>
</form><br>

<h1>Register</h1>
<form action=notepad.py method=post>
  Username:&nbsp;
  <input type=text name=user placeholder=Username><br>
  Password:&nbsp;
  <input type=password name=pass placeholder=Password><br>
  Confirm:&nbsp;
  <input type=password name=confirm placeholder=Password><br>
  <input type=submit name=register value=Register><br>
</form><br>
  '''
  

def note(text, mode):

  if mode == 'read':
    filename = directory + text
    print '<h1>Note ' + text + '</h1><br>'
    print '<textarea id=output name=text readonly wrap=hard>'
    print open(filename, 'r').read()
    print '</textarea><br><br>'
  elif mode == 'write':
    name = genName(6)
    while name in noteNames:
      name = genName(6)
    noteNames.append(name)
    filename = directory + name
    f = open(filename, 'w')
    f.write(text)
    f.close()
    print 'Note ' + name + ' created!<br>'

  print '<a href=notepad.py>Back to Main</a>'

def query():
  
  print '''
<h1>Read a note</h1>
<form action=notepad.py method=post>
  <input type=text name=text><br><br>
  <input type=submit name=read value=Read>
</form><br>

<h1>Create a note</h1>
<form action=notepad.py method=post>
  <textarea id=input name=text placeholder=Type note here... wrap=hard></textarea><br><br>
  <input type=submit name=write value=Write>
</form>
  '''

def register(username, password, confirm):
  if password != confirm:
    print 'Passwords do not match!<br>'
  else:
    users[username] = password
    open('users', 'w').write(username + ' ' + password + '\n')
    print 'Successfully registered!<br>'
  
  print '<a href=notepad.py>Back to Main</a>'

def main():

  header()
  elements = cgi.FieldStorage()
  if elements.has_key('login'):
    auth(elements.getvalue('user'), elements.getvalue('pass'))
  elif elements.has_key('register'):
    register(elements.getvalue('user'), elements.getvalue('pass'), elements.getvalue('confirm'))
  elif elements.has_key('read'):
    note(elements.getvalue('text'), 'read')
  elif elements.has_key('write'):
    note(elements.getvalue('text'), 'write')
  else:
    login()
  footer()

main()
