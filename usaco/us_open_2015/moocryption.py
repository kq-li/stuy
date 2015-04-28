import itertools

f = open('moocrypt.in', 'r')
line = f.readline()
rows = int(line.split(' ')[0])
cols = int(line.split(' ')[1])
alpha = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
maxmoo = 0
winner = []

def countMoo(g):
    count = 0
    for i in g:
        count += i.count('MOO') + i.count('OOM')
    for c in range(cols):
        if 'MOO' in ''.join([g[r][c] for r in range(rows)]) or 'OOM' in ''.join([g[r][c] for r in range(rows)]):
            count += 1
    for r, c in itertools.product(range(rows), range(cols)):
        if r < rows - 2 and c < cols - 2 and ''.join([g[r+i][c+i] for i in range(3)]) == 'MOO':
            count += 1
        if r < rows - 2 and c > 1 and ''.join([g[r+i][c-i] for i in range(3)]) == 'MOO':
            count += 1
        if r > 1 and c < cols - 2 and ''.join([g[r-i][c+i] for i in range(3)]) == 'MOO':
            count += 1
        if r > 1 and c > 1 and ''.join([g[r-i][c-i] for i in range(3)]) == 'MOO':
            count += 1
    return count    

grid = [f.readline().strip() for r in range(rows)]

for char1, char2 in itertools.permutations(alpha, 2):
    if char1 != 'M' and char2 != 'O':
        newgrid = [''.join(['M' if x == char1 else 'O' if x == char2 else x for x in row]) for row in grid]
        count = countMoo(newgrid)
        if count > maxmoo:
            winner = newgrid
            maxmoo = count

f.close()
f = open('moocrypt.out', 'w')
f.write(str(maxmoo))
f.close()
