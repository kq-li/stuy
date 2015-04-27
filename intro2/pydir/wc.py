#!/usr/bin/python

def wc(s):
  numLines = s.count('\n') + 1
  numWords = len(s.split())
  numChars = len(s.replace('\n', ''))
  return [numLines, numWords, numChars]

f = open('dictall.txt', 'r')
print wc(f.read())
f.close()
