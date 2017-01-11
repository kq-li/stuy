def cumSum(L):
  total = 0

  for x in L:
    total += x
    yield total

fin = open('haybales.in', 'r')
fout = open('haybales.out', 'w+')

lines = fin.read().split('\n')
N = int(lines[0].split(' ')[1])
Q = int(lines[0].split(' ')[1])

haybales = [int(i) for i in lines[1].split(' ')]
haybaleList = [1 if i in haybales else 0 for i in range(max(haybales) + 1)]
print 'Created list'
haybaleCList = [i for i in cumSum(haybaleList)]
print 'Created cumulative list'

# print haybaleList
# print haybaleCList
# print ''

def countHaybales(start, end):
  if start > len(haybaleCList) - 1:
    return 0

  if end < 0:
    return 0

  if end > len(haybaleCList) - 1:
    end = len(haybaleCList) - 1

  if start < 0:
    start = 0

  return haybaleCList[end] - haybaleCList[start - 1]

def solve():
  for i in range(Q):
    line = lines[2 + i]
    start = int(line.split(' ')[0])
    end = int(line.split(' ')[1])

    print 'Between %d and %d' % (start, end)

    count = countHaybales(start, end)
    
    print 'Counting: %d' % count
    print ''

    fout.write(str(count) + '\n')

solve()

fin.close()
fout.close()
