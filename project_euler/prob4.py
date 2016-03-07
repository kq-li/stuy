#! /usr/bin/python

import math

def reverse(n):
  if n < 10:
    return n
  
  return (n % 10) * (10 ** int(math.log10(n))) + reverse(n / 10)

def isPalindrome(n):
  return n == reverse(n)

max = 0
a = 0
b = 0
for i in range(1000, 100, -1):
  for j in range(1000, 100, -1):
    if i * j > max and isPalindrome(i * j):
      max = i * j
      a = i
      b = j

print str(a) + " * " + str(b) + " = " + str(max)
