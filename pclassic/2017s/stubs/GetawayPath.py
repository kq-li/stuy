# Fill out the body of this method
def longest_path(grid):
    N = len(grid)
    ans = 0
    return ans

# Make sure this method hasn't changed before submitting!
if __name__ == "__main__":
    with open("GetawayPathIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            N = int(s)
            grid = []
            for i in range(N):
                row = f.readline()
                grid.append([int(x) for x in row.split(" ")])
            print(longest_path(grid))
