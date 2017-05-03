
# Fill out the body of this method
# @param S is an integer array, where each integer denotes 
#         the possible amounts the machine can manufacture
# @param n is an integer indicating the total amount of gold to be distributed
#
def count(S, m, n):
    return -1
 
 
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