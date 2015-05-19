def div23(n):
    if n % 2 == 0 and n % 3 == 0:
        return True
    return False

print div23(4492738)

def div23_between(low, high):
    c = low
    num = 0
    while c <= high:
        if div23(c):
            num += 1
        c += 1
    return num

print div23_between(0, 399154)

def sum_dig(n):
    c = n
    sum = 0
    while c > 0:
        sum += c % 10
        c /= 10
    return sum

print sum_dig(667)

def how_many_11():
    c = 0
    ans = 0
    while c <= 999:
        if sum_dig(c) % 11 == 0:
            ans += 1
        c += 1
    return ans

print how_many_11()

def is_prime(n):
    c = 2
    while c <= n ** 0.5:
        if n % c == 0:
            return False
        c += 1
    return True

print is_prime(12343)
