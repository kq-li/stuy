def can_construct(lines):
    return True

if __name__ == "__main__":
    with open("DeathStarConstructionIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            data = s.split(" ")
            N = int(data[0])
            points = []
            lines = []
            for i in range(2 * N):
                points.append((int(data[3*i + 1]), int(data[3*i + 2]), int(data[3*i + 3])))
            for i in range(N):
                lines.append((points[2*i], points[2*i + 1]))
            if can_construct(lines):
                print("true")
            else:
                print("false")
