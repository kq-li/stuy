#!/usr/bin/python

def addstr(s):
  ans = 0
  lastcomma = 0
  numstr = s + ','
  for i in range(len(numstr)):
    if numstr[i] == ",":
      ans += int(numstr[lastcomma:i])
      lastcomma = i + 1
  return ans

print addstr('81, 3, 477, -58')
