#! /usr/bin/python

import math

def isPrime(n):
  for i in range(2, int(n ** 0.5) + 1):
    if n % i == 0:
      return False

  return True

def primesSmaller(n):
  ret = []
  
  for i in range(2, n):
    if isPrime(i):
      ret.append(i)

  return ret

def greatestPower(n, f):
  return f ** int(math.log(n, f))

def removeDuplicates(L):
  M = []
  
  for i in L:
    if i not in M:
      M.append(i)
      
  return M

L = [greatestPower(20, i) for i in removeDuplicates(primesSmaller(20))]
print reduce(lambda x, y: x * y, L)
