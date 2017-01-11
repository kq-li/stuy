fin = open('haybales.in', 'r')
fout = open('haybales.out', 'w+')

lines = fin.read().split('\n')
N = int(lines[0].split(' ')[1])
Q = int(lines[0].split(' ')[1])

haybales = [int(i) for i in lines[1].split(' ')]
haybaleList = [1 if i in haybales else 0 for i in range(max(haybales) + 1)]

def cumSum(L):
  total = 0

  for x in L:
    total += x
    yield total

def countHaybalesC(start, end):
  if start > len(haybaleCList):
    return 0

  if end > len(haybaleCList):
    end = len(haybaleCList)
  
  return haybaleCList[end - 1] - haybaleCList[start - 1]

def countHaybales(start, end):
  count = 0

  if start > len(haybaleList):
    return count

  if end > len(haybaleList):
    end = len(haybaleList)

  for i in range(start, end):
    count += haybaleList[i]

  return count

def solve():
  for i in range(Q):
    line = lines[2 + i]
    start = int(line.split(' ')[0])
    end = int(line.split(' ')[1])

    print 'Between %d and %d' % (start, end)

    count = countHaybales(start, end)
    countC = countHaybalesC(start, end)
    
    print 'Counting: %d' % count
    print 'Cumulative difference: %d' % countC
    print ''

    fout.write(str(count) + '\n')

def solveC():
  haybaleCList = [i for i in cumSum(haybaleList)]
  

solve()

fin.close()
fout.close()
