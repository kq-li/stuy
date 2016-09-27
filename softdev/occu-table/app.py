from flask import Flask, render_template
from utils import occupation

app = Flask(__name__)

@app.route('/occupations')
def occupations():
  dic = occupation.genDict()
  return render_template('occupation.html', dic = dic)

if __name__ == '__main__':
  app.debug = True
  app.run()
