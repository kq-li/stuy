def score(cards):
    """

    :param cards: list of cards
    :return: score received
    """

    pass


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("PurePazaakIN.txt", "r") as f:
        for line in f:
            data = line.split(' ')
            cards = [int(x) for x in data[1:]]
            print score(cards)

if __name__ == "__main__":
    parse_file_and_call_function()
