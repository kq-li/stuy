# Fill out the body of this method
# s1 and s2 are the first and second strings of 1's and 0's respectively
def is_first_random(s1, s2):
    ans = True
    if ans:
        return "random"
    else:
        return "nonrandom"

if __name__ == "__main__":
    with open("EnigmaIN.txt", "r") as f:
        while True:
            s1 = f.readline()
            if s1 == '':
                break
            s2 = f.readline()
            s1 = s1[:-1]
            s2 = s2[:-1]
            print(is_first_random(s1, s2))
