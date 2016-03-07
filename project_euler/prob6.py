#! /usr/bin/python

num = 100

def sumTo(n):
  return n * (n + 1) / 2

def sumSquares(n):
  return n * (n + 1) * (2 * n + 1) / 6

print abs(sumSquares(num) - sumTo(num) ** 2)
