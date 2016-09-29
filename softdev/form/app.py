from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def default():
  print request.headers
  return render_template('default.html')

@app.route('/auth')
def auth():
  print request.headers
  print request.args
  return 'OK'

if __name__ == '__main__':
  app.debug = True
  app.run()
