import math

def can_profit(prices):
    N = len(prices)
    logprices = []
    dist = []
    for row in prices:
        logprices.append([math.log(x) for x in row])
        dist.append(1e400)
    dist[0] = 0.0
    for i in range(N-1):
        for j in range(N):
            for k in range(N):
                dist[k] = min(dist[k], dist[j] + logprices[j][k])
    for j in range(N):
        for k in range(N):
            if dist[k] > dist[j] + logprices[j][k]:
                return True
    return False

if __name__ == "__main__":
    with open("BlackMarketIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == "":
                break
            rows = s.split(",")
            prices = []
            for row in rows:
                prices.append([float(x) for x in row.split(" ")])
            print("true" if can_profit(prices) else "false")
