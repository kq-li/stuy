#! /usr/bin/python

num = 10001

def isPrime(n):
  for i in range(2, int(n ** 0.5) + 1):
    if n % i == 0:
      return False

  return True

def prime(n):
  if n == 1:
    return 2

  i = 2
  p = 3

  while i < n:
    p += 2
    
    while not isPrime(p):
      p += 2
    
    i += 1

  print p

prime(num)
