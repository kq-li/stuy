import random
import urllib
import collections

def parseLine(line):
  comma = line.rfind(',')
  key = line[:comma].strip('"')
  value = line[comma + 1:]
  return (key, value)

def genDict(filename = 'data/occupations.csv'):
  s = open(filename).read()
  lines = s.split('\r\n')[1:-1]
  dic = collections.OrderedDict()
  
  for line in lines[:-1]:
    parse = parseLine(line)
    key = parse[0]
    value = parse[1]
    link = 'http://lmgtfy.com/?q=' + urllib.quote(key)
    dic[key] = [value, link]

  parse = parseLine(lines[-1])
  key = parse[0]
  value = parse[1]
  dic[key] = [value]

  return dic
