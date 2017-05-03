def poor_pour(arr):
    N = len(arr)
    L = arr
    s1 = 0
    s2 = 1
    e1 = N - 1
    e2 = N - 2
    volume = 0

    def get_volume(start, end):
        n = end - start
        h = min(L[start], L[end])
        w = 5
        return w * h * n + 1 * h * (n - 1) - sum(L[(start + 1):end])

    print L
        
    while s2 < N and e2 >= 0:
        while L[s2] < L[s1] and s2 < N - 1:
            s2 += 1

        while L[e2] < L[e1] and e2 > 0:
            e2 -= 1

        print 'volume in [%d, %d] (left): %d' % (s1, s2, get_volume(s1, s2))
        volume += get_volume(s1, s2)

        print 's2: %d, e1: %d' % (s2, e1)

        if s2 >= e1:
            break

        print 'volume in [%d, %d] (right): %d' % (e2, e1, get_volume(e2, e1))
        volume += get_volume(e2, e1)

        s1 = s2
        s2 += 1

        e1 = e2
        e2 -= 1

        if s2 >= e1:
            break

    return volume



"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
    with open("PoorPourIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            s = s.strip()
            sArr = s.split(" ")
            input = []

            for x in range(0, len(sArr)):
                input.append(int(sArr[x]))

            print poor_pour(input)

if __name__ == "__main__":
    parse_file_and_call_function()
