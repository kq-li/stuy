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
  usersByUser = {line.split(' ')[0]: line.split(' ')[1] for line in open(directory + 'users', 'r').read().split('\n')[:-1]}
  usersBySession = {usersByUser[i]: i for i in usersByUser}
  adminsByUser = {line.split(' ')[0]: line.split(' ')[1] for line in open(directory + 'admin', 'r').read().split('\n')[:-1]}
  adminsBySession = {adminsByUser[i]: i for i in adminsByUser}
  notesByUser = {}
  notesByNote = {}
  for line in open(directory + 'noteData', 'r').read().split('\n')[:-1]:
    line = line.split(' ')
    if line[0] in notesByUser:
      notesByUser[line[0]] += [line[1:]]
    else:
      notesByUser[line[0]] = [line[1:]]
    notesByNote[line[1]] = [line[0]] + line[2:]
except:
  usersByUser = {}
  usersBySession = {}
  adminsByUser = {}
  adminsBySession = {}
  notesByUser = {}
  notesByNote = {}
loggedIn = False
isAdmin = False
output = ''
cookie = Cookie.SimpleCookie()
currentUser = ''
currentSession = ''

def account(mode='home'):
  global output
  if mode == 'home':
    output += '<h1>Account</h1>'
    output += '<h2>Notes</h2>'
    output += '<table><tr><td>Note ID</td><td>Visibility</td><td>Options</td></tr>'
    if currentUser in notesByUser:
      for note in notesByUser[currentUser]:
        output += '<tr><form action=notepad.py method=post>'
        output += ('<td>' + note[0] + '</td>'
                   '<td>' + note[1] + '</td>'
                   '<input type=hidden name=name value=' + note[0] + '>'
                   '<td><input type=submit class=link-btn name=deleteNote value=Delete></td>')
        output += '</form></tr>'
    output += '</table>'
    output += '<h2>Options</h2>'
    output += 'Change password:<br>'
    output += ('<form action=notepad.py method=post>'
               'Old Password:<br>'
               '<input type=password name=oldPassword placeholder=\'Old Password\'><br>'
               'New Password:<br>'
               '<input type=password name=newPassword placeholder=\'New Password\'><br>'
               'Confirm New Password:<br>'
               '<input type=password name=confirm placeholder=\'Confirm New\'><br><br>'
               '<input type=submit class=submit-btn name=changePassword value=Change><br><br>')
    output += 'Delete notes and account: (WARNING: This action cannot be undone!)<br>'
    output += ('<form action=notepad.py method=post>'
               '<input type=password name=password placeholder=Password><br><br>'
               '<input type=submit class=submit-btn name=deleteUser value=Delete><br><br>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home>'
             '</form>'
             '<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout>'
             '</form>')

def admin(mode='home'):
  global output, usersByUser, cookie
  if mode == 'home':
    output += 'Welcome, admin.<br>'
    cookie['admin'] = 0
    cookie['admin']['expires'] = (datetime.datetime.now() + datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
  elif mode == 'users':
    output += '<h1>User Info</h1>'
    output += '<table border=1><tr><td>Username</td><td>Session</td><td>Options</td></tr>'
    for user in usersByUser:
      output += ('<tr>'
                 '<td>' + user + '</td>'
                 '<td>' + usersByUser[user] + '</td>'
                 '<td>'
                 '<form action=notepad.py method=post>'
                 '<input type=hidden name=username value=' + user + '>'
                 '<input type=password name=newPassword placeholder=\'New Password\'>'
                 '<input type=password name=confirm placeholder=\'Confirm Password\'>'
                 '<input type=submit class=link-btn name=changePassword value=\'Change Password\'>'
                 '</form>'
                 '<form action=notepad.py method=post>'
                 '<input type=hidden name=username value=' + user + '>'
                 '<input type=submit class=link-btn name=deleteUser value=Delete>'
                 '</form></td>'
                 '</tr>')
    output += '</table><br>'
  elif mode == 'notes':
    output += '<h1>Note Info</h1>'
    output += '<table border=1><tr><td>Creator</td><td>Note ID</td><td>Visibility</td><td>Options</td></tr>'
    for user in notesByUser:
      for note in notesByUser[user]:
        output += ('<tr>'
                   '<td>' + user + '</td>'
                   '<td>' + note[0] + '</td>'
                   '<td>' + note[1] + '</td>'                   
                   '<td><form action=notepad.py method=get>'
                   '<input type=hidden name=username value=' + user + '>'
                   '<input type=hidden name=name value=' + note[0] + '>'
                   '<input type=submit class=link-btn name=read value=Read>'
                   '</form>'
                   '&nbsp;&nbsp;'
                   '<form action=notepad.py method=post>'
                   '<input type=hidden name=name value=' + note[0] + '>'
                   '<input type=submit class=link-btn name=deleteNote value=Delete>'
                   '</form></td>'
                   '</tr>')
    output += '</table><br>'
  if mode != 'home':
    cookie['admin'] = 1
    cookie['admin']['expires'] = (datetime.datetime.now() + datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout><br>'
             '</form>')

def changePassword(username, newPassword, adminOverride=False):
  global output, cookie, usersByUser, currentSession
  updatedData = ''
  currentSession = md5(username + newPassword)
  for user in usersByUser:
    if user == username:
      updatedData += user + ' ' + currentSession + '\n'
    else:
      updatedData += user + ' ' + usersByUser[user] + '\n'
  open(directory + 'users', 'w').write(updatedData)
  updatedData = ''
  if isAdmin:
    for user in adminsByUser:
      if user == username:
        updatedData  += user + ' ' + currentSession + '\n'
      else:
        updatedData += user + ' ' + adminsByUser[user] + '\n'
    open(directory + 'admin', 'w').write(updatedData)
  cookie['session'] = currentSession
  cookie['session']['expires'] = (datetime.datetime.now() + datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
  output += 'Password changed!<br>'
  if adminOverride:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=users value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')
  else:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=account value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')

def deleteNote(name, adminOverride=False):
  global output, notesByUser
  os.remove(noteDir + name)
  updatedData = ''
  for user in notesByUser:
    for note in notesByUser[user]:
      if note[0] == name:
        notesByUser[user].remove(note)
      else:
        updatedData += user + ' ' + ' '.join(note) + '\n'
  open(directory + 'noteData', 'w').write(updatedData)
  output += 'Note deleted!<br>'
  if adminOverride:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=notes value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')
  else:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=account value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')

def deleteUser(username, adminOverride=False):
  global output
  updatedData = ''
  for user in notesByUser:
    if user != username:
      for note in notesByUser[user]:
        updatedData += user + ' ' + ' '.join(note) + '\n'
  open(directory + 'noteData', 'w').write(updatedData)
  updatedData = ''
  for user in usersByUser:
    if user != username:
      updatedData += user + ' ' + usersByUser[user] + '\n'
  open(directory + 'users', 'w').write(updatedData)
  output += 'Account deleted!<br>'
  if adminOverride:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=users value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')
  else:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=account value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')

def genName(n):
  name = ''
  for i in range(n):
    name += alphanum[random.randrange(len(alphanum))]
  return name

def footer():
  print '</div></body></html>'

def header():
  print ('Content-Type: text/html\n'
         '' + cookie.output() + '\n\n'
         '<html><head>'
         '<link rel=stylesheet type=text/css href=http://fonts.googleapis.com/css?family=Open+Sans>'
         '<link rel=stylesheet type=text/css href=notepad.css>'
         '<title>PasteNote BinPad</title>'
         '</head><body>')

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
  output += ('<form action=notepad.py method=post>'
             '<h1>Login</h1>'
             'Username:<br>'
             '<input type=text name=username placeholder=Username><br>'
             'Password:<br>'
             '<input type=password name=password placeholder=Password><br><br>'
             '<input type=submit class=submit-btn name=login value=Login><br><br>'
             '</form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home>'
             '</form>')
  
def loginUser(username, password):
  global output, cookie, loggedIn, isAdmin
  username = cgi.escape(username)
  password = cgi.escape(password)
  if username in usersByUser and usersByUser[username] == md5(username + password):
    cookie['session'] = md5(username + password)
    cookie['session']['expires'] = (datetime.datetime.now() + datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged in!<br>'
    loggedIn = True
    if username in adminsByUser and md5(username + password) == adminsByUser[username]:
      isAdmin = True
  else:
    output += 'Incorrect username/password.<br>'
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home><br>'
             '</form>')
  if loggedIn:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')
    
def logout():
  global output, cookie, loggedIn, isAdmin
  if 'session' in cookie:
    cookie['session']['expires'] = (datetime.datetime.now() - datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
    output += 'Successfully logged out!<br>'
    loggedIn = False
    isAdmin = False
  else:
    output += ('<form action=notepad.py method=post>'
               'Please login '
               '<input type=submit class=link-btn name=login value=here>'
               '.<br></form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home>'
             '</form>')

def md5(plain):
  return hashlib.md5(plain).hexdigest()

def read():
  global output
  output += ('<form action=notepad.py method=get>'
             '<h1>Read a note</h1>'
             '<input type=text name=name placeholder=\'Note ID\'><br><br>'
             '<input type=submit class=submit-btn name=read value=Read><br><br>'
             '</form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home><br>'
             '</form>'
             '<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout><br>'
             '</form>')

def readNote(name, adminOverride=False):
  global output
  filename = noteDir + name
  if name.isalnum():
    try:
      if isAdmin or notesByNote[name][1] == 'public' or notesByNote[name][0] == currentUser:
        output += ('<h1>Note ' + name + '</h1>'
                   'Created by ' + notesByNote[name][0] + '<br><br>'
                   '<textarea id=output name=text readonly wrap=hard>'
                   '' + open(filename, 'r').read() + ''
                   '</textarea><br><br>')
      else:
        output += 'Invalid filename!<br>'
    except:
      output += 'Invalid filename!<br>'
  else:
    output += 'Invalid filename!<br>'
  if adminOverride:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=notes value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')
  else:
    output += ('<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=read value=Back><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn value=Home><br>'
               '</form>'
               '<form action=notepad.py method=post>'
               '<input type=submit class=link-btn name=logout value=Logout><br>'
               '</form>')

def register():
  global output, cookie
  output += ('<form action=notepad.py method=post>'
             '<h1>Register</h1>'
             'Username:<br>'
             '<input type=text name=username placeholder=Username><br>'
             'Password:<br>'
             '<input type=password name=password placeholder=Password><br>'
             'Confirm Password:<br>'
             '<input type=password name=confirm placeholder=Password><br><br>'
             '<input type=submit class=submit-btn name=register value=Register><br><br>'
             '</form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home>'
             '</form>')

def registerUser(username, password, confirm):
  global output
  username = cgi.escape(username)
  password = cgi.escape(password)
  confirm = cgi.escape(confirm)
  usersByUser[username] = md5(password)
  open(directory + 'users', 'a').write(username + ' ' + md5(username + password) + '\n')
  output += 'Successfully registered!<br>'
  output += ('<form action=notepad.py method=post>'
             'Please login '
             '<input type=submit class=link-btn name=login value=here>'
             '.<br></form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home>'
             '</form>')

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
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout>'
             '</form>')

def write():
  global output
  output += ('<form action=notepad.py method=post>'
             '<h1>Write a note</h1>'
             '<textarea id=input name=data placeholder=\'Type note here...\' wrap=hard></textarea><br><br>'
             'Visibility:&nbsp;&nbsp;'
             '<select name=vis>'
             '<option value=public>Public</option>'
             '<option value=private>Private</option>'
             '</select><br><br>'
             '<input type=submit class=submit-btn name=write value=Write><br><br>'
             '</form>')
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home><br>'
             '</form>'
             '<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout><br>'
             '</form>')


def writeNote(text, vis):
  global output
  noteNames = [note[0] for note in notesByUser.values()]
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
  output += ('<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=write value=Back><br>'
             '</form>'
             '<form action=notepad.py method=post>'
             '<input type=submit class=link-btn value=Home><br>'
             '</form>'
             '<form action=notepad.py method=post>'
             '<input type=submit class=link-btn name=logout value=Logout><br>'
             '</form>')

def main():

  global output, cookie, loggedIn, isAdmin, currentUser, currentSession
  navbar = ('<div id=navbar>'
            '<form action=notepad.py method=post>'
            '<div id=navbar-left>'
            '<input type=submit class=\'navbar-btn navbar-btn-left\' id=home value=\'PasteNote BinPad\'>'
            '</div>'
            '<div id=navbar-right>')
    
  output += '<div id=content>'
  elements = cgi.FieldStorage()

  if 'HTTP_COOKIE' in os.environ:
    cookie = Cookie.SimpleCookie(os.environ['HTTP_COOKIE'])
  if 'session' in cookie and cookie['session'].value in usersBySession:
    loggedIn = True
    currentSession = cookie['session'].value
    currentUser = usersBySession[currentSession]
    if currentUser in adminsByUser and adminsByUser[currentUser] == currentSession:
      isAdmin = True
    else:
      isAdmin = False
    if isAdmin and 'users' in elements:
      admin('users')
    elif isAdmin and 'notes' in elements:
      admin('notes')
    elif 'read' in elements:
      if isAdmin and 'name' in elements and 'admin' in cookie and cookie['admin'].value == '1':
        if elements.getfirst('name').isalnum():
          readNote(elements.getfirst('name'), True)
        else:
          output += 'Invalid filename!<br>'
          admin('notes')
      else:
        if 'admin' in cookie:
          cookie['admin'] = 0
        if 'name' in elements:
          if elements.getfirst('name').isalnum():
            if isAdmin or notesByNote[elements.getfirst('name')][1] == 'public' or notesByNote[elements.getfirst('name')][0] == currentUser:
              readNote(elements.getfirst('name'), False)
            else:
              output += 'Invalid filename!<br>'
              read()
          else:
            output += 'Invalid filename!<br>'
            read()
        else:
          read()
    elif 'write' in elements:
      if 'admin' in cookie:
        cookie['admin'] = 0
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
    elif 'deleteNote' in elements:
      if isAdmin and 'admin' in cookie and cookie['admin'].value == '1':
        if 'name' in elements:
          if elements.getfirst('name') in notesByNote:
            deleteNote(elements.getfirst('name'), True)
          else:
            output += 'Note not found!<br>'
            admin('notes')
        else:
          output += 'Please complete all fields.<br>'
          admin('notes')
      else:
        if 'name' in elements:
          if elements.getfirst('name') in notesByNote:
            if currentUser == notesByNote[elements.getfirst('name')][0]:
              deleteNote(elements.getfirst('name'), False)          
            else:
              output += 'Note not found!<br>'
              account()
          else:
            output += 'Note not found!<br>'
            account()
        else:
          output += 'Please complete all fields.<br>'
          account()
    elif 'logout' in elements:
      logout()
    elif 'account' in elements:
      if 'admin' in cookie:
        cookie['admin'] = 0
      account()
    elif 'changePassword' in elements:
      if isAdmin and 'admin' in cookie and cookie['admin'].value == '1':
        if 'username' in elements and 'newPassword' in elements and 'confirm' in elements:
          if elements.getfirst('newPassword') == elements.getfirst('confirm'):
            changePassword(elements.getfirst('username'), elements.getfirst('newPassword'), True)
          else:
            output += 'Passwords must match!<br>'
            admin('users')
        else:
          output += 'Please complete all fields.<br>'
          admin('users')
      else:
        if 'oldPassword' in elements and 'newPassword' in elements and 'confirm' in elements:
          if elements.getfirst('newPassword') == elements.getfirst('confirm'):
            if md5(currentUser + elements.getfirst('oldPassword')) == currentSession:
              changePassword(currentUser, elements.getfirst('newPassword'), False)
            else:
              output += 'Incorrect password!<br>'
              account()
          else:
            output += 'Passwords must match!<br>'
            account()
        else:
          output += 'Please complete all fields.<br>'
          account()
    elif 'deleteUser' in elements:
      if isAdmin and 'admin' in cookie and cookie['admin'].value == '1':
        if 'username' in elements:
          if elements.getfirst('username') in adminsByUser:
            output += 'Cannot delete admin account.<br>'
            admin('users')
          elif elements.getfirst('username') in usersByUser:
            deleteUser(elements.getfirst('username'), True)
          else:
            output += 'User not found!<br>'
            admin('users')
        else:
          output += 'Please complete all fields.<br>'
          admin('users')
      else:
        if 'password' in elements:
          if isAdmin:
            output += 'Cannot delete admin account.<br>'
            account()
          elif md5(currentUser + elements.getfirst('password')) == currentSession:
            deleteUser(currentUser, False)
            cookie['session']['expires'] = (datetime.datetime.now() - datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
          else:
            output += 'Incorrect password!<br>'
            account()
        else:
          output += 'Please complete all fields.<br>'
          account()
    else:
      if isAdmin:
        admin('home')
      else:
        welcome()
  else:
    if 'admin' in cookie:
      cookie['admin']['expires'] = (datetime.datetime.now() - datetime.timedelta(weeks=1)).strftime('%a, %d-%b-%Y %H:%M:%S EST')
    loggedIn = False
    if 'login' in elements:
      if 'username' in elements and 'password' in elements:
        loginUser(elements.getfirst('username'), elements.getfirst('password'))
      elif not ('username' in elements or 'password' in elements):
        login()
      else:
        output += 'Please complete all fields.<br>'
        login()
    elif 'register' in elements:
      if 'username' in elements and 'password' in elements and 'confirm' in elements:
        if elements.getfirst('password') == elements.getfirst('confirm'):
          if not elements.getfirst('username') in usersByUser:
            registerUser(elements.getfirst('username'), elements.getfirst('password'), elements.getfirst('confirm'))
          else:
            output += 'Username already in use!<br>'
            register()
        else:
          output += 'Passwords must match!<br>'
          register()
      elif not ('username' in elements and 'password' in elements and 'confirm' in elements):
        register()
      else:
        output += 'Please complete all fields.<br>'
        register()
    else:
      home()

  output += '</div>'
     
  if isAdmin:
    navbar += ('<input type=submit class=\'navbar-btn navbar-btn-right\' name=users value=Users>'
               '<input type=submit class=\'navbar-btn navbar-btn-right\' name=notes value=Notes>')
  if loggedIn:
    navbar += ('<input type=submit class=\'navbar-btn navbar-btn-right\' name=read value=Read>'
               '<input type=submit class=\'navbar-btn navbar-btn-right\' name=write value=Write>'
               '<input type=submit class=\'navbar-btn navbar-btn-right\' name=account value=Account>'
               '<input type=submit class=\'navbar-btn navbar-btn-right\' name=logout value=Logout>')
  else:
    navbar += ('<input type=submit class=\'navbar-btn navbar-btn-right\' name=login value=Login>'
               '<input type=submit class=\'navbar-btn navbar-btn-right\' name=register value=Register>')

  navbar += '</div></form></div>'
  
  header()
  print navbar
  print output
  footer()

main()
