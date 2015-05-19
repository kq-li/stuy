def nthObj(s, sep, n):
    index = 0
    lastSep = 0
    if s[-1] != sep:
        s += sep
    for i in range(len(s)):
        if s[i] == sep:
            if index == n:
                return s[(lastSep):i]
            lastSep = i + 1
            index += 1
    return ''
