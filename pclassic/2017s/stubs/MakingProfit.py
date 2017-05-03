def max_profit(matrix):
    return ""


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("MakingProfitIN.txt", "r") as f:
        while True:
            n = f.readline()
            if n == "":
                break
            m = f.readline()
            arr = []
            for x in range(0, 7):
                s = f.readline()
                s = s.strip()
                sArr = s.split(" ")
                input = []
                for y in range(0, len(sArr)):
                    input.append(int(sArr[y]))
                arr.append(input)
            print max_profit(arr)


if __name__ == "__main__":
    parse_file_and_call_function()