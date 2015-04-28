def srfind(search, char):
    count = len(search) - 1
    while count > 0:
        if search[count] == char:
            return count
        count -= 1
    return -1

print srfind('abcbq', 'b')

def srfind2(search, char):
    count = 0
    ans = -1
    while count < len(search):
        if search[count] == char:
            ans = count
        count += 1
    return ans

print srfind('abcbq', 'b')

def sfindp(search, phrase):
    count = 0
    num = len(search) - len(phrase) + 1
    while count < num:
        if search[count:count+len(phrase)] == phrase:
            return count
        count += 1
    return -1

print sfindp('abcdcda', 'cd')
print sfindp('abcbq', 'bq')

