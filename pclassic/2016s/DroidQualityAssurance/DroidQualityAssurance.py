def has_short_circuit(pins):
    """

    :param pins: list of pins
    :return: whether the droid will short circuit
    """

    pass


class Pin(object):

    def __init__(self, uid):
        self.uid = uid
        self.links = []

    def __repr__(self):
        return str(self.uid)


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("DroidQualityAssuranceIN.txt", "r") as f:
        while True:
            num_nodes = int(f.readline())
            test_case(num_nodes, f)

            pound = f.readline()
            if pound == '':
                break
            f.readline()
            f.readline()


def test_case(num_nodes, f):
    id_to_node = {}
    for i in xrange(num_nodes):
        line = f.readline()
        line_split = line.split()

        uid = int(line_split[0].strip()[0:-1])
        neighbor_ids = [int(x) for x in line_split[1:]]

        if uid in id_to_node:
            node = id_to_node[uid]
        else:
            node = Pin(uid)
            id_to_node[uid] = node

        for neighbor_id in neighbor_ids:
            if neighbor_id in id_to_node:
                node.links.append(id_to_node[neighbor_id])
            else:
                n = Pin(neighbor_id)
                node.links.append(n)
                id_to_node[neighbor_id] = n
    print 'true' if has_short_circuit(id_to_node.values()) else 'false'

if __name__ == "__main__":
    parse_file_and_call_function()
