import math
# fill out this function
# layers is a 2 dimensional array containing the heights and corresponding refractive indexes
# target_x is the target x value; the target y value is given by the sume of the heights
# Find the correct value for angle such that light is correctly directed to the 
# target_x, target_y location
def target_angle(layers, target_x):
    angle = 0.0
    return round(angle, 4)

if __name__ == "__main__":
    with open("NewtonsNightmareIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == '':
                break
            N, X = [int(x) for x in s.split()]
            layers = []
            for i in range(N):
                s = f.readline().split()
                layers.append((float(s[0]), float(s[1])))
            print("%.4f" % target_angle(layers, X))
