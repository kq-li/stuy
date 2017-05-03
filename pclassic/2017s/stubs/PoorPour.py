def poor_pour(arr):
    return 0



"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
    with open("PoorPourIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            s = s.strip()
            sArr = s.split(" ")
            input = []

            for x in range(0, len(sArr)):
                input.append(int(sArr[x]))

            print poor_pour(input)

if __name__ == "__main__":
    parse_file_and_call_function()