# Change the body of this method
def best_grouping(scores, G):
    return 0

if __name__ == "__main__":
    with open("JediAcademyIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            data = s.split("--")
            scores = [int(x) for x in data[0].split(",")]
            G = int(data[1])
            print(best_grouping(scores, G))
