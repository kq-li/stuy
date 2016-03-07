#! /usr/bin/python

def isPrime(n):
  for i in range(2, int(n ** 0.5) + 1):
    if n % i == 0:
      return False

  return True

def primeFactor(n):
  if isPrime(n):
    return [n]

  for i in range(2, int(n ** 0.5)):
    if n % i == 0 and isPrime(i):
      return [i] + primeFactor(n / i)

print max(primeFactor(13195))
print max(primeFactor(600851475143))

