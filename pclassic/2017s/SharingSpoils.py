
# Fill out the body of this method
# @param S is an integer array, where each integer denotes 
#         the possible amounts the machine can manufacture
# @param n is an integer indicating the total amount of gold to be distributed
#
def count(C, m, N):
    if N == 0:
        return 1
    if len(C) == 1:
        return 1 if N % C[0] == 0 else 0

    ans = 0

    for i in range(0, N + 1, C[0]):
        ans += count(C[1:], m - 1, N - i)

    return ans
        
 
 
"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
    with open('SharingSpoilsIN.txt', 'r') as f:
        p = 0
        lines = f.read().split("\n")
        while p + 1 < len(lines):
            dims = lines[p].split(" ")
            n, m = int(dims[0]), int(dims[1])
            arr = lines[p + 1].split(" ")
            a = []
            for i in arr:
                a.append(int(i))
            print(count(a, m, n))
            p = p + 2

if __name__ == "__main__":
    parse_file_and_call_function()
