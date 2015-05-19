#!/usr/bin/python

import sys

def nWords(filename):
  f = open(filename, 'r')
  words = f.read().split()
  return len(words)

def longest(filename):
  f = open(filename, 'r')
  longWords = ['']
  longest = 0
  words = f.read().split()
  for i in words:
    if len(i) == longest:
      longWords.append(i)
    if len(i) > longest:
      longWords = [i]
      longest = len(i)
  return longWords

if len(sys.argv) != 3:
  print "Usage: ./io.py <flag> <filename>\n"
else:
  flag = sys.argv[1]
  filename = sys.argv[2]
  if flag == 'num':
    print nWords(filename)
  if flag == 'long':
    print longest(filename)

