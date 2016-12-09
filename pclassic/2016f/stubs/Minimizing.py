class GraphNode:
    def __init__(self, n):
        self.n = n
        self.links = []

    def shortestDist(self, ends, numShips):
        dist = 0
        nodes = [(self, dist)]
        
        while len(nodes) > 0:
            root = nodes[0]
            rootNode = root[0]
            rootDist = root[1]
            
            if rootNode.n in ends:
                return rootDist

            if rootDist >= numShips:
                return -1

            for node in rootNode.links:
                nodes.append((node, rootDist + 1))

            nodes.pop(0)

        return -1

# M is the number of ports.
# ships is a list of lists, each list represents the stops for one ship
# start and end are the IDs of the start and end port respectively
def minimum_stops(M, ships, start, end):
    shipsAtPort = {}
    shipsFromShip = {}
    shipNodes = {}
    numShips = 0

#     print ships

    for ship in range(len(ships)):
        ports = ships[ship]
        
        for port in ports:
            if port in shipsAtPort:
                shipsAtPort[port].append(ship)
            else:
                shipsAtPort[port] = [ship]

    for port in shipsAtPort:
        ships = shipsAtPort[port]

        for s1 in ships:
            for s2 in ships:
                if s1 != s2:
                    if s1 in shipsFromShip:
                        shipsFromShip[s1].append(s2)
                    else:
                        shipsFromShip[s1] = [s2]

    for ship in shipsFromShip:
        shipNode = GraphNode(ship)
        shipNodes[ship] = shipNode
#         shipNode.links = shipsFromShip[ship]

    for ship in shipNodes:
        shipNode = shipNodes[ship]
#         print shipsFromShip[ship]
        shipNode.links = {shipNodes[i] for i in shipsFromShip[ship]}
#         print shipNode.links
        
    numShips = len(shipNodes)
    shipsAtStart = shipsAtPort[start]
    shipsAtEnd = shipsAtPort[end]

    if len(shipsAtStart) > len(shipsAtEnd):
        shipsAtStart = shipsAtPort[end]
        shipsAtEnd = shipsAtPort[start]

    minDist = -1
    
    for ship in shipsAtStart:
        dist = shipNodes[ship].shortestDist(shipsAtEnd, numShips)
#         print dist
        
        if minDist == -1 or dist < minDist:
            minDist = dist

    return minDist

if __name__ == "__main__":
    with open("MinimizingIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == '':
                break
            N, M, S, E = [int(x) for x in s.split()]
            ships = []
            for i in range(N):
                ships.append([int(x) for x in f.readline().split()])
            print(minimum_stops(M, ships, S, E))
