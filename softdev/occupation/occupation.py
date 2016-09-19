#! /usr/bin/python

import random

s = open('occupations.csv').read()
lines = s.split('\r\n')[1:-1]

def genDict(lines):
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

      
dic = genDict(lines)
print weightedOccupation(dic)      
