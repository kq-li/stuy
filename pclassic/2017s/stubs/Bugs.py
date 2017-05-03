# Fill out the body of this method
def has_odd_cycle(N, M, edges):
    ans = False
    return ans

# Make sure this method hasn't changed before submitting!
if __name__ == "__main__":
    with open("BugsIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            s = s.split(" ")
            N = int(s[0])
            M = int(s[1])
            edges = []
            for i in range(M):
                edge = f.readline().split(" ")
                edges.append((int(edge[0]), int(edge[1])))
            print("true" if has_odd_cycle(N, M, edges) else "false")
