from flask import Flask, render_template, request, url_for, session, redirect
import hashlib
import binascii

app = Flask(__name__)
auth_file = None
users = {}

def hash256(password):
  return hashlib.sha256(password).hexdigest()

def isUser(username):
  return username in users

def authUser(username, password):
  return isUser(username) and users[username] == hash256(password)

def addUser(username, password):
  passhash = hash256(password)
  users[username] = passhash
  auth_file.write(username + ' ' + passhash + '\n')
  auth_file.flush()
  
def loggedIn():
  return 'username' in session and isUser(session['username'])

@app.route('/')
def home():
  if loggedIn():
    return redirect(url_for('welcome'))
  else:
    return redirect(url_for('loginForm'))

@app.route('/welcome')
def welcome():
  if loggedIn():
    return render_template('welcome.html')
  else:
    return redirect(url_for('loginForm', message = 'test'))
    
@app.route('/login')
def loginForm():
  if loggedIn():
    return redirect(url_for('welcome'))
  else:
    return render_template('login.html')

@app.route('/login', methods = ['POST'])
def login():
  username = request.form['username']
  password = request.form['password']
  print username, password, hash256(password)
  print users
  print authUser(username, password)
  
  if loggedIn():
    print 'Already logged in'
    return redirect(url_for('welcome'))
  elif authUser(username, password):
    print 'Successfully logged in'
    session['username'] = username
    return redirect(url_for('welcome'))
  else:
    print 'Incorrect username or password'
    return render_template('login.html', message = 'Incorrect username or password')

@app.route('/logout')
def logout():
  session.pop('username')
  return redirect(url_for('login'))

@app.route('/register')
def registerForm():
  if loggedIn():
    return redirect(url_for('welcome'))
  else:
    return render_template('register.html')

@app.route('/register', methods = ['POST'])
def register():
  username = request.form['username']
  password = request.form['password']

  if username.find(' ') == -1 and not isUser(username):
    print 'Successfully registered'
    addUser(username, password)
    session['username'] = username
    return redirect(url_for('welcome'))
  else:
    print 'Invalid username'
    return render_template('register.html', message = 'Invalid username!')

if __name__ == '__main__':
  auth_file = open('data/users', 'a+')
  lines = auth_file.read().split('\n')[:-1]

  for line in lines:
    parts = line.split(' ')
    username = parts[0]
    passhash = parts[1]
    users[username] = passhash

  app.debug = True
  app.config.from_object('config')
  app.secret_key = app.config['SECRET_KEY']
  app.run()
