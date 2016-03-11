#! /usr/bin/python

cap = 2000000

def isPrime(n):
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False

    return True

sum = 0
p = 2

while True:
    while not isPrime(p):
        p += 1

    if p >= cap:
        break
    
    sum += p
    p += 1

print sum
