# Fill out the body of this method
# @param l is length of the box
# @param w is width of the box
# @param h is height of the box
def treasure_chest(l, w, h):
    return 0
 
# Do not modify this method 
def parse_file_and_call_function():
    with open ("TreasureIN.txt", "r") as f:
        for line in f:
            dimensions = [int(dimension) for dimension in line.split(' ')]
            print treasure_chest(dimensions[0], dimensions[1], dimensions[2]) 

if __name__ == '__main__':
    parse_file_and_call_function()