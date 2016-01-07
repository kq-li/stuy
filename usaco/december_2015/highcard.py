#! /usr/bin/python

import copy

fin = open('highcard.in', 'r')
fout = open('highcard.out', 'w+')

s = fin.read().split('\n')
N = int(s[0])
cards = range(2 * N)
elsie = map(lambda x: int(x) - 1, s[1:1 + N])
bessie = copy.copy(cards)
for card in elsie:
  bessie.remove(card)

def leastHigherCard(card):
  for i in bessie:
    if i > card:
      return i
  return -1

# does not account for card saving/evaluating highest value, etc.
points = 0
for round in range(N):
  if leastHigherCard(elsie[round]) != -1:
    points += 1

fout.write(str(points))
fin.close()
fout.close()
