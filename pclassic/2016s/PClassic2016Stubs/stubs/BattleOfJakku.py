#Fill out the body of this method
def true_operations(arraylength, array1, array2, operator):
    outputarray = []

    return outputarray

def parse_file_and_call_function():
    with open("BattleOfJakkuIN.txt", "r") as f:
        while True:
            arraylength = f.readline()
            array1 = f.readline().split(' ')
            array2 = f.readline().split(' ')
            operator = f.readline()
            x = true_operations(arraylength, array1, array2, operator)

            print (x)



if __name__ == "__main__":
    parse_file_and_call_function()