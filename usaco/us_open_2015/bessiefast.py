f = open('geteven.in', 'r')
numLines = int(f.readline())
print numLines
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
for b in B:
    for e in E:
        for s in S:
            for i in I:
                for g in G:
                    for o in O:
                        for m in M:
                            possibilities.append((b+e+s+s+i+e)*(g+o+e+s)*(m+o+o))
count = 0
for i in possibilities:
    if i % 2 == 0:
        count += 1
f.close()
f = open('geteven.out', 'w')
f.write(str(count))
f.close()
