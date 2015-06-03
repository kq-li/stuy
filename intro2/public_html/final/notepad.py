#! /usr/bin/python

import cgi
import Cookie
import datetime
import hashlib
import os
import random
import re
import sys

__import__('cgitb').enable()

directory = 'notes/'
alphanum = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
noteNames = os.listdir(directory)
try:
  users = dict([line.split(' ') for line in open('users', 'r').read().split('\n')[:-1]])
except:
  users = {}
sessions = {users[i]: i for i in users}
output = ''
cookie = Cookie.SimpleCookie()


def auth(username, password):
  global output, cookie
  if username in users and users[username] == hashlib.md5(username + password).hexdigest():
    expires = datetime.datetime.now() + datetime.timedelta(weeks=1)
    cookie['session'] = hashlib.md5(username + password).hexdigest()
    cookie['session']['expires'] = expires.strftime("%a, %d-%b-%Y %H:%M:%S PST")
    output += 'Successfully logged in!<br>'
  else:
    output += 'Incorrect username/password.<br>'
  output += '<a href=notepad.py>Back to Main</a>'

def genName(n):
  name = ''
  for i in range(n):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def header():
  print 'Content-Type: text/html'
  print cookie
  print '\n'
  print '<html><head>'
  print '<title>PasteNote BinPad</title>'
  print '<link rel=stylesheet type=text/css href=notepad.css>'
  print '</head><body>'

def footer():
  print '</body></html>'

def login():

  global output
  output += '''
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

  global output
  if mode == 'read':
    filename = directory + text
    if text.isalpha():
      try:
        s = open(filename, 'r').read()
        output += '<h1>Note ' + text + '</h1><br>'
        output += '<textarea id=output name=text readonly wrap=hard>'
        output += s
        output += '</textarea><br>'
      except:
        output += 'Invalid filename!<br>'
    else:
      output += 'Invalid filename!<br>'
  elif mode == 'write':
    name = genName(6)
    while name in noteNames:
      name = genName(6)
    noteNames.append(name)
    filename = directory + name
    f = open(filename, 'w')
    f.write(text)
    f.close()
    output += 'Note ' + name + ' created!<br>'

  output += '<a href=notepad.py>Back to Main</a>'

def query():
  
  global output
  output += '''
<h1>Read a note</h1>
<form action=notepad.py method=post>
  <input type=text name=text><br><br>
  <input type=submit name=mode value=Read>
</form><br>

<h1>Create a note</h1>
<form action=notepad.py method=post>
  <textarea id=input name=text placeholder=\'Type note here...\' wrap=hard></textarea><br><br>
  <input type=submit name=mode value=Write>
</form>
  '''

def register(username, password, confirm):

  global output
  if password != confirm:
    output += 'Passwords do not match!<br>'
  elif username in users:
    output += 'Username already in use!<br>'
  else:
    users[username] = hashlib.md5(password).hexdigest()
    open('users', 'a').write(username + ' ' + hashlib.md5(username + password).hexdigest() + '\n')
    output += 'Successfully registered!<br>'
  
  output += '<a href=notepad.py>Back to Main</a>'

def main():

  global output

  elements = cgi.FieldStorage()

  if 'HTTP_COOKIE' in os.environ:
    cookie = Cookie.SimpleCookie(os.environ['HTTP_COOKIE'])
    if 'session' in cookie and cookie['session'].value in sessions:
      if 'mode' in elements and 'text' in elements:
        note(elements.getfirst('text'), elements.getfirst('mode').lower())
      else:
        query()
    else:
      output += 'Please log in or register.<br>'
  else:
    if 'login' in elements:
      auth(elements.getfirst('user'), elements.getfirst('pass'))
    elif 'register' in elements:
      register(elements.getfirst('user'), elements.getfirst('pass'), elements.getfirst('confirm'))
    else:
      login()

  header()
  print output
  footer()

main()

def main_local():

  global output, cookie
#  elements = {'register': 'Register', 'user': 'admin', 'pass': 'password', 'confirm': 'password'}
  elements = {'login': 'Login', 'user': 'admin', 'pass': 'password'}
  cookie['session'] = 'e3274be5c857fb42ab72d786e281b4b8'
  if cookie != Cookie.SimpleCookie():
    if 'session' in cookie and cookie['session'].value in sessions:
      if 'mode' in elements and 'text' in elements:
        note(elements['text'], elements['mode'].lower())
      else:
        query()
    else:
        output += 'Please log in or register.<br>'
  else:
    if 'login' in elements:
      auth(elements['user'], elements['pass'])
    elif 'register' in elements:
      register(elements['user'], elements['pass'], elements['confirm'])
    else:
      login()

  header()
  print output
  footer()

#main_local()
