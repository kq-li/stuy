from flask import Flask, render_template, request

app = Flask(__name__)
admin = ('admin', 's3cur3p4ssw0rd')

@app.route('/')
def login():
  print request.headers
  return render_template('default.html')

@app.route('/auth', methods = ['POST'])
def auth():
  username = request.form['username']
  password = request.form['password']

  if (username == admin[0] and password == admin[1]):
    return 'Logged in successfully!'
  else:
    return 'Incorrect username or password!'

if __name__ == '__main__':
  app.debug = True
  app.run()
