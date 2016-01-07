#! /usr/bin/python

fin = open('lightson.in', 'r')
fout = open('lightson.out', 'w+')

s = fin.read().split('\n')
first = map(int, s[0].split(' '))
N = first[0]
M = first[1]

rooms = [[[] for j in range(N)] for i in range(N)]

for i in s[1:1 + M]:
  i = map(lambda x: int(x) - 1, i.split(' '))
  rooms[i[0]][i[1]].append((i[2], i[3]))

lit = [[False for j in range(N)] for i in range(N)]
lit[0][0] = True

visited = [[False for j in range(N)] for i in range(N)]
visited[0][0] = True

def getNeighbors(coords):
  x = coords[0]
  y = coords[1]
  ret = []
  if x > 0:
    ret.append((x - 1, y))
  if y > 0:
    ret.append((x, y - 1))
  if x < N - 1:
    ret.append((x + 1, y))
  if y < N - 1:
    ret.append((x, y + 1))
  return ret

def printMatrix(a):
  for i in a:
    print i
  print ''
  
def getSwitches(coords):
  return rooms[coords[0]][coords[1]]

def isLit(coords):
  return lit[coords[0]][coords[1]]

def light(coords):
  lit[coords[0]][coords[1]] = True

def isVisited(coords):
  return visited[coords[0]][coords[1]]
    
def visit(coords):
  visited[coords[0]][coords[1]] = True

def lightRooms(coords):
  for room in getSwitches(coords):
    light(room)

def explore(room):
  visit(room)
  lightRooms(room)
  for neighbor in getNeighbors(room):
    if not isVisited(neighbor) and isLit(neighbor):
      explore(neighbor)

def doneExploring():
  for i in range(N):
    for j in range(N):
      if isLit((i, j)) and not isVisited((i, j)):
        return False
  return True

explore((0, 0))
while not doneExploring():
  explore((0, 0))

numLit = sum([i.count(True) for i in lit])
fout.write(str(numLit))

fin.close()
fout.close()

  


