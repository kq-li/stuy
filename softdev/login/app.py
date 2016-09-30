from flask import Flask, render_template, request
import hashlib
import binascii

app = Flask(__name__)
auth_file = None
users = {}

def addUser(username, passhash):
  users[username] = passhash
  auth_file.write(username + ' ' + passhash)
  auth_file.flush()
  
@app.route('/')
def home():
  print request.headers
  return render_template('default.html')

def hash256(password):
  return hashlib.sha256(password).hexdigest()

@app.route('/login', methods = ['POST'])
def login():
  username = request.form['username']
  password = request.form['password']

  if username in users and hash256(password) == users[username]:
    return render_template('auth.html', message = 'Successfully logged in!')
  else:
    return render_template('auth.html', message = 'Incorrect username or password!')

@app.route('/register', methods = ['POST'])
def register():
  username = request.form['username']

  if username.find(' ') != -1:
    return render_template('auth.html', message = 'Username must not contain spaces!')
  else:
    password = request.form['password']
    addUser(username, hash256(password))
    return render_template('auth.html', message = 'Successfully registered!')

if __name__ == '__main__':
  auth_file = open('data/users', 'a+')
  lines = auth_file.read().split('\n')

  for line in lines:
    parts = line.split(' ')
    username = parts[0]
    passhash = parts[1]
    users[username] = passhash

  print users
        
  app.debug = True
  app.run()
