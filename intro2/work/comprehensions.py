def seclar(L):
    L.remove(max(L))
    return max(L)

def isAnagram(w1, w2):
    if len(w1) != len(w2):
        return False
    for i in w1:
        if i in w2:
            w2 = w2[:w2.index(i)] + w2[w2.index(i) + 1:]
        else:
            return False
    return True

def isAnagramSort(w1, w2):
    w1 = [i for i in w1]
    w1.sort()
    w2 = [i for i in w2]
    w2.sort()
    return w1 == w2

def isAnagramChr(w1, w2):
    return [w1.count(chr(x)) for x in range(256)] == [w2.count(chr(x)) for x in range(256)]

def isAnagramSorted(w1, w2):
    return sorted(w1) == sorted(w2)
    
