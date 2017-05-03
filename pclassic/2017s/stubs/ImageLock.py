# Fill out the body of this method, img is a 2d array (list of lists)
# where the pixel value at (x, y) is given by img(x, y)
def isCat(img):
    return True

# Make sure this method hasn't changed before submitting!
if __name__ == "__main__":
    with open("ImageLockIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            s = s.split(" ")
            W = int(s[0])
            H = int(s[1])
            img = [ [ 0 for i in range(H) ] for j in range(W) ]
            for y in range(H):
                s = f.readline()
                s = s.split(" ")
                for x in range(W):
                    img[x][y] = int(s[x])

            if isCat(img):
                print('cat')
            else:
                print('random')



