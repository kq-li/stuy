# Fill out the body of this method
def count_triples(measurements):
    return 0

# Before submitting, make sure this method hasn't been changed
if __name__ == "__main__":
    with open("ForceFluctuationsIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            N = int(s)
            s = f.readline()
            data = s.split(" ")
            measurements = [int(x) for x in data]
            print(count_triples(measurements))
