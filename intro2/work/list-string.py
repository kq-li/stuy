def esrever(l):
    return [i[::-1] for i in l]

def av(l):
    l = [float(i) for i in l if type(i) == type(0.0) or type(i) == type(0)]
    return (sum(l)/len(l) if len(l) != 0 else 0)

def smallestPos(l):
    return l.index(min(l))

def sortNums(l):
    ans = []
    for i in range(len(l)):
        ans.append(min(l))
        l.remove(min(l))
    return ans

def numInWords(n):
    units = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
    tens = ['', '', 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
    ans = ''
    while n >= 0:
        if n >= 100:
            ans += units[n / 100] + ' hundred' + ('' if n % 100 == 0 else ' and ')
            n = -1 if n % 100 == 0 else n % 100
        elif n >= 20:
            ans += tens[n / 10] + ('' if n % 10 == 0 else '-')
            n = -1 if n % 10 == 0 else n % 10
        else:
            ans += units[n]
            n = -1
    return ans
