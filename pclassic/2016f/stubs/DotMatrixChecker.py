# Fill out the body of this method
# N is an integer representing the desired height
def draw_dot_matrix_letter(N):
    # remember to add a newline character (\n) at the end of each line of text you output
    letter = ""
    return letter

if __name__ == "__main__":
    with open("DotMatrixCheckerIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == '':
                break
            N = int(s)
            print draw_dot_matrix_letter(N)
