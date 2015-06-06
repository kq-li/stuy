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

def genName(n):
  name = ''
  for i in range(n):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def footer():
  print '</body></html>'

def header():
  print 'Content-Type: text/html'
  print cookie
  print '\n'
  print '<html><head>'
  print '<title>PasteNote BinPad</title>'
  print '<link rel=stylesheet type=text/css href=notepad.css>'
  print '</head><body>'

def home():

  global output
  output += '''
Welcome to PasteNote BinPad!<br>
Please <a href=#login>login</a> or <a href=#register>register</a> to proceed.<br>
  '''

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
  '''
  
def loginUser(username, password):
  global output, cookie
  username = cgi.escape(username)
  password = cgi.escape(password)
  if username in users and users[username] == md5(username + password):
    expires = datetime.datetime.now() + datetime.timedelta(weeks=1)
    cookie['session'] = md5(username + password)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged in!<br>'
  else:
    output += 'Incorrect username/password.<br>'

def logout():
  
  global output, cookie
  if 'session' in cookie:
    expires = datetime.datetime.now() - datetime.timedelta(weeks=1)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged out!<br>'
  else:
    output += 'Please log in.<br>'

def md5(plain):
  return hashlib.md5(plain).hexdigest()

def read():

  global output
  output += '''
<h1>Read a note</h1>
<form action=notepad.py method=post>
  <input type=text name=text placeholder=\'Note ID\'><br><br>
  <input type=submit name=read value=Read><br><br>
</form>
  '''

def readNote(text):

  global output
  filename = directory + text
  if text.isalnum():
    try:
      s = re.split('<br>|\n', open(filename, 'r').read())
      if s[0] == 'public' or s[1] == cookie['session'].value:
        output += '<h1>Note ' + text + '</h1>'
        output += 'Created by ' + sessions[s[1]] + '<br><br>'
        output += '<textarea id=output name=text readonly wrap=hard>'
        output += ''.join(s[2:])
        output += '</textarea><br>'
      else:
        output += 'Invalid filename!<br>'
    except:
      output += 'Invalid filename!<br>'
  else:
    output += 'Invalid filename!<br>'

def register():

  global output
  output += '''
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

def registerUser(username, password, confirm):

  global output
  username = cgi.escape(username)
  password = cgi.escape(password)
  confirm = cgi.escape(confirm)
  users[username] = md5(password)
  open('users', 'a').write(username + ' ' + md5(username + password) + '\n')
  output += 'Successfully registered!<br>'

def write():

  global output
  output += '''
<h1>Write a note</h1>
<form action=notepad.py method=post>
  <textarea id=input name=text placeholder=\'Type note here...\' wrap=hard></textarea><br><br>
  Visibility:&nbsp;&nbsp;
  <select name=mode>
    <option value=public>Public</option>
    <option value=private>Private</option>
  </select><br><br>
  <input type=submit name=write value=Write><br><br>
</form>
  '''

def writeNote(text, mode):

  global output
  name = genName(6)
  while name in noteNames:
    name = genName(6)
  noteNames.append(name)
  filename = directory + name
  f = open(filename, 'w')
  data = mode + '\n' + cookie['session'].value + '\n' + cgi.escape(text)
  f.write(data)
  f.close()
  output += 'Note ' + name + ' created!<br>'

def main():

  global output, cookie
  elements = cgi.FieldStorage()
  output += '<div id=navbar>'

  if 'HTTP_COOKIE' in os.environ:
    output += '''
<form action=notepad.py method=post>
  <input type=submit class=navbar-button id=home value=Home>
  <input type=submit class=navbar-button id=read name=read value=Read>
  <input type=submit class=navbar-button id=write name=write value=Write>
  <input type=submit class=navbar-button id=logout name=logout value=Logout>
</form>
    '''
    cookie = Cookie.SimpleCookie(os.environ['HTTP_COOKIE'])
    if 'session' in cookie and cookie['session'].value in sessions:
      if 'read' in elements:
        if 'text' in elements:
          readNote(elements.getfirst('text'))
        else:
          read()
      elif 'write' in elements:
        if 'text' in elements and 'mode' in elements:
          if elements.getfirst('mode') in ['public', 'private']:
            writeNote(elements.getfirst('text'), elements.getfirst('mode'))
          else:
            output += 'Please specify a visibility setting.<br>'
        elif not ('text' in elements or 'mode' in elements):
          write()
        else:
          output += 'Please complete all fields.<br>'
          write()
      elif 'logout' in elements:
        logout()
      else:
        output += '<a href=#read>Read</a> or <a href=#write>write</a> a note.'
    else:
      output += 'Please log in or register.<br>'
  else:

    output += '''
<form action=notepad.py method=post>
  <input type=submit class=navbar-button value=Home>
  <input type=submit class=navbar-button id=login name=login value=Login>
  <input type=submit class=navbar-button id=register name=register value=Register>
</form>
    '''

    if 'login' in elements:
      if 'user' in elements and 'pass' in elements:
        loginUser(elements.getfirst('user'), elements.getfirst('pass'))
      elif not ('user' in elements or 'pass' in elements):
        login()
      else:
        output += 'Please complete all fields.<br>'
        login()
    elif 'register' in elements:
      if 'user' in elements and 'pass' in elements and 'confirm' in elements:
        if elements.getfirst('pass') == elements.getfirst('confirm'):
          if not elements.getfirst('user') in users:
            registerUser(elements.getfirst('user'), elements.getfirst('pass'), elements.getfirst('confirm'))
          else:
            output += 'Username already in use!<br>'
            register()
        else:
          output += 'Passwords must match!<br>'
          register()
      elif not ('user' in elements and 'pass' in elements and 'confirm' in elements):
        register()
      else:
        output += 'Please complete all fields.<br>'
        register()
    else:
      home()

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
      elif 'logout' in elements:
        logout()
      else:
        query()
    else:
      output += 'Please log in or register.<br>'
  else:
    if 'login' in elements:
      if 'user' in elements and 'pass' in elements:
        auth(elements['user'], elements['pass'])
      else:
        output += 'Please complete all fields.<br>'
        login()
    elif 'register' in elements:
      if 'user' in elements and 'pass' in elements and 'confirm' in elements:
        if elements['pass'] == elements['confirm']:
          if not elements['user'] in users:
            register(elements['user'], elements['pass'], elements['confirm'])
          else:
            output += 'Username already in use!<br>'
            login()
        else:
          output += 'Passwords must match!<br>'
          login()
      else:
        output += 'Please complete all fields.<br>'
        login()
    else:
      login()

  header()
  print output
  footer()

#main_local()
