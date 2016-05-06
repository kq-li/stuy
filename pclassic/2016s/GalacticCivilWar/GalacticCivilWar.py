import math

#Fill out the body of this method
def different_numbers(numbers, n):
    return 0


def parse_file_and_call_function():
    with open("GalacticCivilWarIN.txt", "r") as f:
        while True:
            data = f.readline().split(' ')
            numbers = f.readline().split(' ')
            n = data[1]
            x = different_numbers(numbers, n)

            print (x)

if __name__ == "__main__":
    parse_file_and_call_function()