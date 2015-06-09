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
try:
  userData = {line.split(' ')[0]: line.split(' ')[1:] for line in open(directory + 'users', 'r').read().split('\n')[:-1]}
  users = {user: userData[user][0] for user in userData}
  sessions = {users[i]: i for i in users}
  adminData = {line.split(' ')[0]: line.split(' ')[1] for line in open(directory + 'admin', 'r').read().split('\n')[:-1]}
  noteData = {}
  noteUsers = {}
  for line in open(directory + 'noteData', 'r').read().split('\n')[:-1]:
    line = line.split(' ')
    if line[0] in noteData:
      noteData[line[0]] += [line[1:]]
    else:
      noteData[line[0]] = [line[1:]]
    noteUsers[line[1]] = [line[0]] + line[2:]
except:
  userData = {}
  users = {}
  sessions = {}
  adminData = {}
  noteData = {}
  noteUsers = {}
loggedIn = False
isAdmin = False
output = ''
cookie = Cookie.SimpleCookie()
currentUser = ''
currentSession = ''

def account():
  global output
  output += '<h1>Account</h1>'
  output += '<table><tr><td>Note ID</td><td>Visibility</td><td>Options</td></tr>'
  if currentUser in noteData:
    for note in noteData[currentUser]:
      output += '<tr><form action=notepad.py method=post>'
      for i in range(2):
        output += '<td>' + note[i] + '</td>'
      output += '<input type=hidden name=user value=' + currentUser + '>'
      output += '<input type=hidden name=note value=' + note[0] + '>'
      output += '<td><input type=submit class=link-btn name=delete value=Delete></td>'
      output += '</form></tr>'
  output += '</table><br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout>
</form>
  '''

def admin(mode='home'):
  global output, users, userData
  if mode == 'home':
    output += 'Welcome, admin.<br>'
  elif mode == 'users':
    output += '<h1>User Info</h1>'
    output += '<table border=1><tr><td>Username</td><td>Session</td></tr>'
    for user in users:
      output += '<tr><td>' + user + '</td><td>' + users[user] + '</td></tr>'
    output += '</table><br>'
  elif mode == 'notes':
    output += '<h1>Note Info</h1>'
    output += '<table border=1><tr><td>Creator</td><td>Note ID</td><td>Visibility</td><td>Options</td></tr>'
    for user in noteData:
      for note in noteData[user]:
        output += '<tr><form action=notepad.py method=post>'
        output += '<td>' + user + '</td>'
        for i in range(2):
          output += '<td>' + note[i] + '</td>'
        output += '<input type=hidden name=user value=' + user + '>'
        output += '<input type=hidden name=note value=' + note[0] + '>'
        output += '<td><input type=submit class=link-btn name=delete value=Delete></td>'
        output += '</form></tr>'
    output += '</table><br>'        
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
  '''

def delete(username, name):
  global output, noteData
  if name in noteUsers and (isAdmin or username == noteUsers[name][0]):
    os.remove(noteDir + name)
    updatedData = ''
    for user in noteData:
      for note in noteData[user]:
        if note[0] == name:
          noteData[username].remove(note)
        else:
          updatedData += user + ' ' + ' '.join(note) + '\n'
    open(directory + 'noteData', 'w').write(updatedData)
    output += 'Note deleted!<br>'
  else:
    output += 'Note not found!<br>'
  if isAdmin:
    output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=hidden name=notes>
  <input type=submit class=link-btn name=admin value=Back><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
    '''
  else:
    output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=account value=Back><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
    '''

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
  output += ('<form action=notepad.py method=post>'
             'Welcome!<br>'
             'Please '
             '<input type=submit class=link-btn name=login value=login>'
             ' or '
             '<input type=submit class=link-btn name=register value=register>'
             ' to proceed.<br></form>')

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
  '''
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''
  
def loginUser(username, password):
  global output, cookie, loggedIn, isAdmin
  username = cgi.escape(username)
  password = cgi.escape(password)
  if username in users and users[username] == md5(username + password):
    expires = datetime.datetime.now() + datetime.timedelta(weeks=1)
    cookie['session'] = md5(username + password)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged in!<br>'
    loggedIn = True
    if username in adminData and md5(username + password) == adminData[username]:
      isAdmin = True
  else:
    output += 'Incorrect username/password.<br>'
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
  '''
  if loggedIn:
    output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
    '''

    
def logout():
  global output, cookie, loggedIn, isAdmin
  if 'session' in cookie:
    expires = datetime.datetime.now() - datetime.timedelta(weeks=1)
    cookie['session']['expires'] = expires.strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged out!<br>'
    loggedIn = False
    isAdmin = False
  else:
    output += ('<form action=notepad.py method=post>'
               'Please login '
               '<input type=submit class=link-btn name=login value=here>'
               '.<br></form>')
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
<form action=notepad.py method=get>
  <h1>Read a note</h1>
  <input type=text name=name placeholder=\'Note ID\'><br><br>
  <input type=submit class=submit-btn name=read value=Read><br><br>
</form>
  '''
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
  '''


def readNote(name):
  global output
  filename = noteDir + name
  if name.isalnum():
    try:
      if isAdmin or noteUsers[name][1] == 'public' or noteUsers[name][0] == currentUser:
        output += '<h1>Note ' + name + '</h1>'
        output += 'Created by ' + currentUser + '<br><br>'
        output += '<textarea id=output name=text readonly wrap=hard>'
        output += open(filename, 'r').read()
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
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
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
  output += ('<form action=notepad.py method=post>'
             'Please login '
             '<input type=submit class=link-btn name=login value=here>'
             '.<br></form>')
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home>
</form>
  '''

def welcome():
  global output
  output += 'Welcome ' + currentUser + '!<br>'
  output += ('<form action=notepad.py method=post>'
             'Use the navbar above or the links here to '
             '<input type=submit class=link-btn name=read value=read>'
             ', '
             '<input type=submit class=link-btn name=write value=write>'
             ', or manage your '
             '<input type=submit class=link-btn name=account value=account>'
             '.<br></form>')
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout>
</form>
  '''

def write():
  global output
  output += '''
<form action=notepad.py method=post>
  <h1>Write a note</h1>
  <textarea id=input name=data placeholder=\'Type note here...\' wrap=hard></textarea><br><br>
  Visibility:&nbsp;&nbsp;
  <select name=vis>
    <option value=public>Public</option>
    <option value=private>Private</option>
  </select><br><br>
  <input type=submit class=submit-btn name=write value=Write><br><br>
</form>
  '''
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
  '''


def writeNote(text, vis):
  global output
  noteNames = [note[0] for note in noteData.values()]
  name = genName(6)
  while name in noteNames:
    name = genName(6)
  text = cgi.escape(text)
  open(directory + 'noteData', 'a').write(currentUser + ' ' + name + ' ' + vis + '\n')
  open(noteDir + name, 'w').write(text)
  output += 'Note ' + name + ' created!<br>'
  output += ('<form action=notepad.py method=get>'
             'See it '
             '<input type=hidden name=name value=' + name + '>'
             '<input type=submit class=link-btn name=read value=here>'
             '.<br></form>')
  output += '''
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=read value=Back><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn value=Home><br>
</form>
<form action=notepad.py method=post>
  <input type=submit class=link-btn name=logout value=Logout><br>
</form>
  '''

def main():

  global output, cookie, loggedIn, isAdmin, adminData, currentUser, currentSession
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
      currentSession = cookie['session'].value
      currentUser = sessions[currentSession]
      if currentUser in adminData and adminData[currentUser] == currentSession:
        isAdmin = True
      else:
        isAdmin = False
      if isAdmin and 'users' in elements:
        admin('users')
      elif isAdmin and 'notes' in elements:
        admin('notes')
      elif 'read' in elements:
        if 'name' in elements:
          readNote(elements.getfirst('name'))
        else:
          read()
      elif 'write' in elements:
        if 'data' in elements and 'vis' in elements:
          if elements.getfirst('vis') in ['public', 'private']:
            writeNote(elements.getfirst('data'), elements.getfirst('vis'))
          else:
            output += 'Please specify a visibility setting.<br>'
        elif not ('data' in elements or 'vis' in elements):
          write()
        else:
          output += 'Please complete all fields.<br>'
          write()
      elif 'delete' in elements:
        if 'user' in elements and 'note' in elements:
          delete(elements.getfirst('user'), elements.getfirst('note'))
        else:
          output += 'Note not found!<br>'
      elif 'logout' in elements:
        logout()
      elif 'account' in elements:
        account()
      else:
        if isAdmin:
          admin('home')
        else:
          welcome()
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
     
  if isAdmin:
    navbar += '''
<input type=submit class='navbar-btn navbar-btn-right' name=users value=Users>
<input type=submit class='navbar-btn navbar-btn-right' name=notes value=Notes>
    '''
  if loggedIn:
    navbar += '''
<input type=submit class='navbar-btn navbar-btn-right' name=read value=Read>
<input type=submit class='navbar-btn navbar-btn-right' name=write value=Write>
<input type=submit class='navbar-btn navbar-btn-right' name=account value=Account>
<input type=submit class='navbar-btn navbar-btn-right' name=logout value=Logout>
    '''
  else:
    navbar += '''
<input type=submit class='navbar-btn navbar-btn-right' name=login value=Login>
<input type=submit class='navbar-btn navbar-btn-right' name=register value=Register>
    '''

  navbar += '</div></div></form>'
  
  header()
  print navbar
  print output
  footer()

main()
