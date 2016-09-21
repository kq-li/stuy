from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def default():
  L = [1, 2, 3, 4]
  return render_template('basic.html', message = 'Blarg', test = L)

@app.route('/route1')
def route1():
  return 'route1'

@app.route('/route2')
def route2():
  return 'route2'

if __name__ == '__main__':
  app.debug = True
  app.run()
  
