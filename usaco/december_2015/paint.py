#! /usr/bin/python

fin = open('paint.in', 'r')
fout = open('paint.out', 'w+')

s = fin.read().split('\n')
john = map(lambda x: int(x), s[0].split(' '))
bessie = map(lambda x: int(x), s[1].split(' '))
fence = [0] * 101
for i in range(john[0], john[1]):
  fence[i] = 1
for i in range(bessie[0], bessie[1]):
  fence[i] = 1
fout.write(str(fence.count(1)))
fin.close()
fout.close()
