# Fill out the body of this method, xcoords and ycoords are lists
# representing the x and y coordinate of the points respectively
def count_parallelograms(xcoords, ycoords):
    return 0
            
# Make sure this method hasn't changed before submitting!
if __name__ == "__main__":
    with open("LaserBeamVaultIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            s = s.split(" ")
            N = int(s[0])
            xcoords = []
            ycoords = []
            for i in range(N):
                s = f.readline()
                s = s.split(" ")
                xcoords.append(int(s[0]))
                ycoords.append(int(s[1]))
            print(count_parallelograms(xcoords, ycoords))
