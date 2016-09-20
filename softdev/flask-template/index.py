from flask import Flask

app = Flask(__name__)

def toHTML(head, body):
  return tag(tag(head, 'head') + tag(body, 'body'), 'html')

def tag(text, tag, options={}):
  return '<' + tag + ''.join([' ' + key + '=' + options[key] for key in options]) + '>' + text + '</' + tag + '>'
  
@app.route('/')
def default():
  print toHTML('', tag('default', 'h1'))
  return toHTML('', tag('default', 'h1'))

@app.route('/route1')
def route1():
  return toHTML('', tag('route1', 'h1'))

@app.route('/route2')
def route2():
  return toHTML('', tag('route2', 'h1'))

if __name__ == '__main__':
  app.run()
  
