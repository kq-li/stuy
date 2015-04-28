def toUpper(s):
    lower = 'abcdefghijklmnopqrstuvwxyz'
    upper = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    ans = ''
    for i in s:
        if i in lower:
            ans += upper[lower.find(i)]
        else:
            ans += i
    return ans

def encrypt(s):
    plain  = 'defghijklmnopqrstuvwxyzabcDEFGHIJKLMNOPQRSTUVWXYZABC'
    cipher = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
    ans = ''
    for i in s:
        if i in plain:
            ans += cipher[plain.find(i)]
        else:
            ans += i
    return ans

def isSameName(name1, name2):
    if name1.upper() == name2.upper():
        return True
    return False

def capWord(word):
    return word[0].upper() + word[1:].lower()

def capName(name):
    last = 0
    tmp = name + ' '
    ans = ''
    for i in range(len(tmp)):
        if tmp[i] == ' ':
            ans += capWord(tmp[last:i]) + tmp[i]
            last = i + 1
    ans = ans[:-1]
    return ans

def firstLast(name):
    for i in range(len(name)):
        if name[i] == ',':
            return name[i+2:] + ' ' + name[:i]

def firstLastSequence(names):
    last = 0
    ans = ''
    for i in range(len(names)):
        if names[i] == ';':
            ans += firstLast(names[last:i]) + names[i]
            last = i + 1
    return ans

def fileClassifier(filename):
    suffixes = ['jpg'    , 'jpeg'   , 'mp3'  , 'nlogo'  , 'py']
    types    = ['picture', 'picture', 'music', 'NetLogo', 'Python']
    suffix = filename[-filename[::-1].find('.'):].lower()
    if suffix in suffixes:
        return types[suffixes.index(suffix)]
    
def caesarEnc(s, n):
    plainlower  = 'abcdefghijklmnopqrstuvwxyz'
    plainupper  = plainlower.upper()
    cipherlower = plainlower[n % 26:] + plainlower[:n % 26]
    cipherupper = cipherlower.upper()
    ans = ''
    for i in s:
        if i in plainlower:
            ans += cipherlower[plainlower.find(i)]
        elif i in plainupper:
            ans += cipherupper[plainupper.find(i)]
        else:
            ans += i
    return ans
    
def caesarDec(s, n):
    return caesarEnc(s, -n)
    
def TCP(s):
    teams = ['jets', 'knicks', 'yankees', 'mets', 'rangers', 'islanders', 'red bulls', 'cosmos', 'giants']
    for i in range(1, 26):
        decrypted = caesarDec(s, i)
        for j in teams:
            if j in decrypted.lower():
                return decrypted
    return -1
        
print TCP('Zw Z yrmv jvve wlikyvi zk zj sp jkreuzex fe kyv jyfcuvij fw Xzrekj.')    
