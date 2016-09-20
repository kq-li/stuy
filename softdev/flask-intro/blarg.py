#! venv/bin/python

from flask import Flask

app = Flask(__name__)

@app.route('/')
def blarg():
  return __name__ + 'blarg'

if __name__ == '__main__':
  app.run()
