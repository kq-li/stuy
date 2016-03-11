#! /usr/bin/python

import math

sum = 1000

def isInt(n):
    return n == int(n)

for b in range(2, 1000):
    for a in range(1, b):
        c = math.sqrt(a * a + b * b)

        if isInt(c) and a + b + c == sum:
            c = int(c)
            print a, b, c, a * b * c
        
        
        
    
