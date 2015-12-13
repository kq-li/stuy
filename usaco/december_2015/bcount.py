#! /usr/bin/python

fin = open('bcount.in', 'r')
fout = open('bcount.out', 'w+')

s = fin.read().split('\n')

cows = []

for i in range(len(s)):
  if i == 0:
    j = s[i].split(' ')
    N = int(j[0])
    Q = int(j[1])
  elif i < N + 1:
    cows.append(int(s[i]))
  elif i < N + Q + 1:
    counts = [0, 0, 0]
    j = s[i].split(' ')
    for cow in cows[int(j[0]) - 1:int(j[1])]:
      counts[cow - 1] += 1
    fout.write(str(counts[0]) + ' ' + str(counts[1]) + ' ' + str(counts[2]) + '\n')

fin.close()
fout.close()
