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

directory = '../../final/'
noteDir = directory + 'notes/'
alphanum = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
noteNames = os.listdir(noteDir)
try:
  users = dict([line.split(' ') for line in open(directory + 'users', 'r').read().split('\n')[:-1]])
except:
  users = {}
sessions = {users[i]: i for i in users}
admin = tuple(open(directory + 'admin', 'r').read().split('\n')[:-1])
loggedIn = False
output = ''
cookie = Cookie.SimpleCookie()

def genName(n):
  name = ''
  for i in range(n):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def footer():
  print '</div></body></html>'

def header():
  print 'Content-Type: text/html'
  print cookie
  print '\n'
  print '<html><head>'
  print '<link rel=stylesheet type=text/css href=http://fonts.googleapis.com/css?family=Open+Sans>'
  print '<link rel=stylesheet type=text/css href=notepad.css>'
  print '<title>PasteNote BinPad</title>'
  print '</head><body>'

def home():

  global output
  output += '''
<form action=notepad.py method=post>
  Welcome!<br>
  Please 
  <input type=submit class=link-btn name=login value=login>
  or 
  <input type=submit class=link-btn name=register value=register>
  to proceed.<br>
</form>
  '''

def login():

  global output
  output += '''
<form action=notepad.py method=post>
  <h1>Login</h1>
  Username:<br>
  <input type=text name=user placeholder=Username><br>
  Password:<br>
  <input type=password name=pass placeholder=Password><br><br>
  <input type=submit class=submit-btn name=login value=Login><br><br>
</form>

<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''
  
def loginUser(username, password):
  global output, cookie, loggedIn
  username = cgi.escape(username)
  password = cgi.escape(password)
  if username in users and users[username] == md5(username + password):
    expires = datetime.datetime.now() + datetime.timedelta(weeks=1)
    cookie['session'] = md5(username + password)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged in!<br>'
    loggedIn = True
  else:
    output += 'Incorrect username/password.<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

    
def logout():
  
  global output, cookie, loggedIn
  if 'session' in cookie:
    expires = datetime.datetime.now() - datetime.timedelta(weeks=1)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged out!<br>'
    loggedIn = False
  else:
    output += 'Please log in.<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def md5(plain):
  return hashlib.md5(plain).hexdigest()

def read():

  global output
  output += '''
<form action=notepad.py method=post>
  <h1>Read a note</h1>
  <input type=text name=text placeholder=\'Note ID\'><br><br>
  <input type=submit class=submit-btn name=read value=Read><br><br>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def readNote(text):

  global output
  filename = noteDir + text
  if text.isalnum():
    try:
      s = re.split('<br>|\n', open(filename, 'r').read())
      if s[0] == 'public' or s[1] == cookie['session'].value:
        output += '<h1>Note ' + text + '</h1>'
        output += 'Created by ' + sessions[s[1]] + '<br><br>'
        output += '<textarea id=output name=text readonly wrap=hard>'
        output += ''.join(s[2:])
        output += '</textarea><br><br>'
      else:
        output += 'Invalid filename!<br>'
    except:
      output += 'Invalid filename!<br>'
  else:
    output += 'Invalid filename!<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=read value=Back><br>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def register():

  global output, cookie
  output += '''
<form action=notepad.py method=post>
  <h1>Register</h1>
  Username:<br>
  <input type=text name=user placeholder=Username><br>
  Password:<br>
  <input type=password name=pass placeholder=Password><br>
  Confirm Password:<br>
  <input type=password name=confirm placeholder=Password><br><br>
  <input type=submit class=submit-btn name=register value=Register><br><br>
</form>

<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def registerUser(username, password, confirm):

  global output
  username = cgi.escape(username)
  password = cgi.escape(password)
  confirm = cgi.escape(confirm)
  users[username] = md5(password)
  open(directory + 'users', 'a').write(username + ' ' + md5(username + password) + '\n')
  output += 'Successfully registered!<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def write():

  global output
  output += '''
<form action=notepad.py method=post>
  <h1>Write a note</h1>
  <textarea id=input name=text placeholder=\'Type note here...\' wrap=hard></textarea><br><br>
  Visibility:&nbsp;&nbsp;
  <select name=mode>
    <option value=public>Public</option>
    <option value=private>Private</option>
  </select><br><br>
  <input type=submit class=submit-btn name=write value=Write><br><br>
</form>

<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def writeNote(text, mode):

  global output
  name = genName(6)
  while name in noteNames:
    name = genName(6)
  noteNames.append(name)
  filename = noteDir + name
  f = open(filename, 'w')
  data = mode + '\n' + cookie['session'].value + '\n' + cgi.escape(text)
  f.write(data)
  f.close()
  output += 'Note ' + name + ' created!<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=write value=Back>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def main():

  global output, cookie, loggedIn
  navbar = '''
<form action=notepad.py method=post>
  <div id=navbar>
    <div id=navbar-left>
      <input type=submit class='navbar-btn navbar-btn-left' id=home value='PasteNote BinPad'>
    </div>
    <div id=navbar-right>
  '''
    
  output += '<div id=content>'
  elements = cgi.FieldStorage()

  if 'HTTP_COOKIE' in os.environ:
    cookie = Cookie.SimpleCookie(os.environ['HTTP_COOKIE'])
    if 'session' in cookie and cookie['session'].value in sessions:
      loggedIn = True
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
        output += 'Welcome ' + sessions[cookie['session'].value] + '!<br>'
        output += '''
<form action=notepad.py method=post>
  Follow the links here or in the navigation bar above to  
  <input type=submit class=link-btn name=read value=read>
  or 
  <input type=submit class=link-btn name=write value=write>
  a note.<br>
</form>

<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout>
</form>
        '''
    else:
      loggedIn = False
      expires = datetime.datetime.now() - datetime.timedelta(weeks=1)
      cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
      output += 'Please log in or register.<br>'
  else:
    loggedIn = False
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

  output += '</div>'
      
  if loggedIn:
    navbar += '''
<input type=submit class='navbar-btn navbar-btn-right' id=read name=read value=Read>
<input type=submit class='navbar-btn navbar-btn-right' id=write name=write value=Write>
<input type=submit class='navbar-btn navbar-btn-right' id=logout name=logout value=Logout>
    '''
  else:
    navbar += '''
<input type=submit class='navbar-btn navbar-btn-right' id=login name=login value=Login>
<input type=submit class='navbar-btn navbar-btn-right' id=register name=register value=Register>
    '''

  navbar += '</div></div></form>'
  
  header()
  print navbar
  print output
  footer()

main()
