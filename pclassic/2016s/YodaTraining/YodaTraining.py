def smallest_larger_weapon(root, k):
    """

    :param root: root of the balanced binary search tree of weapons
    :param k: an integer
    :return: the smallest weapon value in the tree larger than k
    """

    pass


class TreeNode(object):

    def __init__(self, value=None, left=None, right=None):
        self.left = left
        self.right = right
        self.value = value

    def __repr__(self):
        return str(self.value)


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("YodaTrainingIN.txt", "r") as f:
        while True:
            num_nodes = int(f.readline())
            k_values = [int(k) for k in f.readline().split(' ')]
            test_case(num_nodes, k_values, f)

            pound = f.readline()
            if pound == '':
                break
            f.readline()
            f.readline()


def test_case(num_nodes, k_values, f):
    id_to_node = {}
    for i in xrange(num_nodes):
        line = f.readline()
        uid, value, left_id, right_id = line.split(' ')

        uid = int(uid[0:-1])
        value = int(value)
        left_id = int(left_id)
        right_id = int(right_id)

        if uid in id_to_node:
            node = id_to_node[uid]
            node.value = value
        else:
            node = TreeNode(value)
            id_to_node[uid] = node

        if left_id == -1:
            node.left = None
        elif left_id in id_to_node:
            node.left = id_to_node[left_id]
        else:
            node.left = TreeNode()
            id_to_node[left_id] = node.left

        if right_id == -1:
            node.right = None
        elif right_id in id_to_node:
            node.right = id_to_node[right_id]
        else:
            node.right = TreeNode()
            id_to_node[right_id] = node.right

    root = id_to_node[1]

    for k in k_values:
        print smallest_larger_weapon(root, k)

if __name__ == "__main__":
    parse_file_and_call_function()
