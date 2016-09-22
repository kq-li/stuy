import random
from flask import Flask, render_template;

app = Flask(__name__);

def genDict(filename = 'occupations.csv'):
  s = open(filename).read()
  lines = s.split('\r\n')[1:-1]
  dic = {}
  
  for line in lines:
    comma = line.rfind(',')
    key = line[:comma].strip('"')
    value = float(line[comma + 1:])
    dic[key] = value

  return dic
  
def weightedOccupation(dic):
  if not dic:
    return None

  total = dic.pop('Total')
  r = random.randrange(int(10 * total)) / 10.0
  #print 'Random: ' + str(r)
  cur = 0
  
  for key in dic:
    cur += dic[key]
    #print 'Cur: ' + str(cur) + ' ' + key
    
    if r < cur:
      return key

@app.route('/occupations')
def renderOccupationTable():
  dic = genDict()
  return render_template('occupation.html', dic = dic)  

if __name__ == '__main__':
  app.debug = True
  app.run()
