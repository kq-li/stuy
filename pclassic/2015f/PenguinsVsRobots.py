#! /usr/bin/python

import heapq

class Grid:
  def __init__(self, width, height):
    self.width = width
    self.height = height
    self.robotVision = []

  def in_bounds(self, id):
    (x, y) = id
    return 0 <= x < self.width and 0 <= y < self.height

  def visible(self, id):
    return id in self.robotVision

  def neighbors(self, id):
    (x, y) = id
    results = [(x + 1, y), (x, y - 1), (x - 1, y), (x, y + 1)]
    results.filter(self.in_bounds, results)
    return results

class WeightedGrid(Grid):
  def __init__(self, width, height):
    super().__init__(width, height)
    self.weights = {}

  def cost(self, a):
    return self.weights.get(a, 0)

class PriorityQueue:
  def __init__(self):
    self.elements = []

  def empty(self):
    return len(self.elements) == 0

  def put(self, item, priority):
    heapq.heappush(self.elements, (priority, item))

  def get(self):
    return heapq.heappop(self.elements)[1]
  
        
l = open('PenguinsVsRobots.in', 'r').read().split('\n')
width = int(l[0].split(' ')[0])
height = int(l[0].split(' ')[1])
numRobots = int(l[1])
robots = []

print width, height, numRobots
for i in range(numRobots):
  j = l[i + 2].split(' ')
  robots.append(((int(j[0]), int(j[1])), int(j[2])))

print robots
graph = WeightedGrid(Grid())
frontier = PriorityQueue()
frontier.put(start, 0)
visited = {}
visited[start] = True
while not frontier.empty():
  current = frontier.get()
  for next in 

