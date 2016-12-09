def greeting(S):
    """

    :param S: A person's name
    :return: "Hello <name>"
    """

    # TODO: implement
    return ''


def parse_file_and_call_function():
    with open("GreetingIN.txt", "r") as f:
        for line in f:
            print greeting(line.strip())


if __name__ == "__main__":
    parse_file_and_call_function()
