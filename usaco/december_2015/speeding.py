#! /usr/bin/python

fin = open('speeding.in', 'r')
fout = open('speeding.out', 'w+')

s = fin.read().split('\n')
numRoadSegs = int(s[0].split(' ')[0])
numBessieSegs = int(s[0].split(' ')[1])

roadSegs = []
for i in s[1:numRoadSegs + 1]:
  i = i.split(' ')
  roadSegs.append((int(i[0]), int(i[1])))

bessieSegs = []
for i in s[numRoadSegs + 1:numRoadSegs + numBessieSegs + 1]:
  i = i.split(' ')
  bessieSegs.append((int(i[0]), int(i[1])))

road = []
for seg in roadSegs:
  road += [seg[1]] * seg[0]
bessie = []
for seg in bessieSegs:
  bessie += [seg[1]] * seg[0]
speeding = [(bessie[i] - road[i]) for i in range(100)]

fout.write(str(max(max(speeding), 0)))

fin.close()
fout.close()


