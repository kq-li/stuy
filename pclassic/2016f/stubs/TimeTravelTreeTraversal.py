class TreeNode:
    def __init__(self, n):
        self.n = n
        self.parent = None
        self.left = None
        self.right = None

    def isRoot(self):
        return not bool(self.parent)

    def isLeaf(self):
        return not (bool(self.left) or bool(self.right))
        
    def parentList(self):
        ret = [self.n]
        node = self

        while not node.isRoot():
            node = node.parent
            ret.append(node.n)

        return ret

def distBetween(L1, L2):
    for i in range(len(L1)):
        for j in range(len(L2)):
            if L1[i] == L2[j]:
                return i + j

    return -1
    
def time_travel_tree_traversal(splits):
    """

    :param splits: a list of tuples where each tuple is an (a, b, c) int triple
                   indicating that timeline a spawned timelines b and c
    :return: an integer indicating the largest distance between two timelines
    """

    # TODO: implement
    nodes = {}

    for split in splits:
        node = split[0]
        left = split[1]
        right = split[2]

        if node not in nodes:
            nodes[node] = TreeNode(node)

        if left not in nodes:
            nodes[left] = TreeNode(left)

        if right not in nodes:
            nodes[right] = TreeNode(right)

    for split in splits:
        node = nodes[split[0]]
        left = nodes[split[1]]
        right = nodes[split[2]]
        node.left = left
        node.right = right
        left.parent = node
        right.parent = node

    leaves = []
        
    for n in nodes:
        node = nodes[n]

        if node.isLeaf():
            leaves.append(node)

    maxDist = 0
    
    for n1 in leaves:
        for n2 in leaves:
            if n1 != n2:
                dist = distBetween(n1.parentList(), n2.parentList())

                if dist > maxDist:
                    maxDist = dist

    return maxDist

def parse_file_and_call_function():
    with open("TimeTravelTreeTraversalIN.txt", "r") as f:
        while True:
            start_test_case = f.readline()
            if not start_test_case:
                break
            num_lines = int(start_test_case)
            splits = []
            for i in xrange(num_lines):
                root, left, right = f.readline().split()
                splits.append((int(root), int(left), int(right)))
            print time_travel_tree_traversal(splits)


if __name__ == '__main__':
    parse_file_and_call_function()
