def closest_vowel(s):
    """

    :param str: string containing at least one vowel
    :return: vowel closest to head or tail of string
    """
    return 'a'


"""FILE PARSING - DO NOT MODIFY"""


def parse_file_and_call_function():
    with open("CipherCrackingIN.txt", "r") as f:
        for line in f:
            print(closest_vowel(line.rstrip()))

if __name__ == "__main__":
    parse_file_and_call_function()
