from flask import Flask

app = Flask(__name__)

@app.route('/')
def default():
  return 'default'

@app.route('/route1')
def route1():
  return 'route1'

@app.route('/route2')
def route2():
  return 'route2'

if __name__ == '__main__':
  app.run()
  
