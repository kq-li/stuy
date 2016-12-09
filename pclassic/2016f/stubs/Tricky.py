def is_perfect_square(n):
    return int(n ** 0.5) ** 2 == n

def pythagorean_triples(c):
    """

    :param c: the length of the aqueduct you want to construct.
    :return: a list containing all possible configurations of the other two
             sides that are of positive integer length. Output each
             configuration as a separate element in a list in the format a b
             where a is in ascending order and b is in descending order in
             respect to the other configurations.
    """

    # TODO: implement

    ret = []

    for a in range(1, c):
        sq = c ** 2 - a ** 2
        
        if is_perfect_square(sq):
            b = int(sq ** 0.5)
            ret.append(a)
            ret.append(b)
        
    return ret

def parse_file_and_call_function():
    with open("TrickyIN.txt", "r") as f:
        for line in f:
            print list(pythagorean_triples(int(line)))

if __name__ == "__main__":
    parse_file_and_call_function()
