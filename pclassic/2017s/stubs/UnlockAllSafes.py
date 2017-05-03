# @param matrix, a list of lists of integers
# @return a list of integers in the described order. 
def spiralOrder(matrix):
    solution = []
    return solution

"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
    with open('UnlockAllSafesIN.txt', 'r') as f:
        p = 0
        lines = f.read().split("\n")
        while p < len(lines):
            if lines[p] == '':
                break
            n = int(lines[p])
            a = []
            p = p + 1
            for i in range(n):
                a.append(map(lambda x: int(x), lines[p].split(" ")))
                p = p + 1
            ans = spiralOrder(a)
            print (' '.join(map(lambda x: str(x), ans)))

if __name__ == "__main__":
    parse_file_and_call_function()