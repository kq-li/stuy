def nextHailstone(n):
    if n % 2 == 0:
        return n / 2
    else:
        return 3 * n + 1

def hailstoneSequence(n):
    c = n
    print c
    while c != 1:
        c = nextHailstone(c)
        print c
