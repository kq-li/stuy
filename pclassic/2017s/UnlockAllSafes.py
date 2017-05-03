# @param matrix, a list of lists of integers
# @return a list of integers in the described order. 
def spiralOrder(matrix):
    # print 'matrix', matrix

    if len(matrix) == 1:
        return matrix[0]

    if len(matrix) == 2:
        return matrix[0] + matrix[1][::-1]

    top = matrix[0]
    right = [row[-1] for row in matrix][1:]
    bottom = matrix[-1][::-1][1:]
    left = [row[0] for row in matrix][::-1][1:-1]
    interior = [row[1:-1] for row in matrix[1:-1]]

    # print 'top', top
    # print 'right', right
    # print 'bottom', bottom
    # print 'left', left
    # print 'interior', interior
    
    return top + right + bottom + left + spiralOrder(interior)

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
