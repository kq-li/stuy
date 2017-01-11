fin = open('moocast.in', 'r')
fout = open('moocast.out', 'w+')

lines = fin.read().strip('\n').split('\n')
N = int(lines[0])
cows = []

class Cow:
  def __init__(self, cid, x, y, p):
    self.cid = cid
    self.x = x
    self.y = y
    self.p = p
    self.inRange = None
    self.numInRange = 0
    self.reachable = None
    self.numReachable = 0

  def __repr__(self):
    return self.__str__()
    
  def __str__(self):
    return str(self.cid)

  def infoStr(self):
    return '%d: (%d, %d, %d)' % (self.cid, self.x, self.y, self.p)

  def isCowInRange(self, other):
    return (self.x - other.x) * (self.x - other.x) + (self.y - other.y) * (self.y - other.y) < self.p * self.p

  def init(self, cows):
    self.inRange = set()

    for cow in cows:
      if cow != self and cow not in self.inRange and self.isCowInRange(cow):
        self.inRange.add(cow)
        self.numInRange += 1

  def reachableCows(self, cows, cowsSoFar = set()):
    cowsSoFar.add(self)

    print 'cow %d can reach %s directly' % (self.cid, str(self.inRange))
    print 'cow %d cowsSoFar: %s' % (self.cid, str(cowsSoFar))

    for cow in self.inRange:
      if cow not in cowsSoFar:
        cowsSoFar |= cow.reachableCows(cows, cowsSoFar)

    print 'cow %d cowsSoFar: %s' % (self.cid, str(cowsSoFar))
    print ''
    
    return cowsSoFar

cid = 0

for line in lines[1:]:
  line = line.split(' ')
  x = int(line[0])
  y = int(line[1])
  p = int(line[2])
  cows.append(Cow(cid, x, y, p))
  cid += 1

for cow in cows:
  cow.init(cows)
  print cow.infoStr()
  print ''
  
maxReachable = 0

for cow in cows:
  print 'Finding reachable from cow %d' % cow.cid
  reachable = cow.reachableCows(cows, set())
  print 'Reachable: %s\n\n' % str(reachable)
  length = len(reachable)

  if length > maxReachable:
    maxReachable = length

fout.write(str(maxReachable) + '\n')

fin.close()
fout.close()
