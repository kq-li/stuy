def sumBetween(a, b):
    sum = 0
    if a <= b:
        c = a
        while c <= b:
            sum += c
            c += 1
    else:
        c = b
        while c <= a:
            sum += c
            c += 1
    return sum
