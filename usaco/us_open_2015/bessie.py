import itertools

f = open('geteven.in', 'r')
numLines = int(f.readline())
possibilities = []
B = []
bapp = B.append
E = []
eapp = E.append
S = []
sapp = S.append
I = []
iapp = I.append
G = []
gapp = G.append
O = []
oapp = O.append
M = []
mapp = M.append
read = f.readline
for i in range(numLines):
    line = read()
    if line[0] == 'B':
        bapp(int(line[2:]))
    elif line[0] == 'E':
        eapp(int(line[2:]))
    elif line[0] == 'S':
        sapp(int(line[2:]))
    elif line[0] == 'I':
        iapp(int(line[2:]))
    elif line[0] == 'G':
        gapp(int(line[2:]))
    elif line[0] == 'O':
        oapp(int(line[2:]))
    elif line[0] == 'M':
        mapp(int(line[2:]))
        
for (b, e, s, i, g, o, m) in list(itertools.product(B, E, S, I, G, O, M)):
    x = (b+e+s+s+i+e)*(g+o+e+s)*(m+o+o)
    if x % 2 == 0:
        possibilities.append(x)

f.close()
f = open('geteven.out', 'w')
f.write(str(len(possibilities)))
f.close()

