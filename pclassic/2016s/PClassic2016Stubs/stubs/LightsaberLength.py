def measure(power, radius):
    return 0

def parse_file_and_call_function():
    with open("LightsaberLengthIN.txt", "r") as f:
        array = f.readline().split(' ')

        for x in range(0, len(array)//2):
            power = int(array[2 * x])
            radius = int(array[2 * x + 1])
            print (measure(power,radius))



if __name__ == "__main__":
    parse_file_and_call_function()
