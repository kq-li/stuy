def getCharIndex(c):
    return ord(c) - ord('a')

def anagram_tester(word_list):
    """

    :param word_list: list of words
    :return: largest set of words that are all anagrams in alphabetical order
    """

    # TODO: implement
    primes = [2, 3, 5, 7, 11,
              13, 17, 19, 23, 29,
              31, 37, 41, 43, 47,
              53, 59, 61, 67, 71,
              73, 79, 83, 89, 97,
              101]
    word_dict = {}

    for word in word_list:
        product = 1

        for c in word:
            product *= primes[getCharIndex(c)]
        
        if product in word_dict:
            word_dict[product].append(word)
        else:
            word_dict[product] = [word]

    maxWords = 0
    maxWordProduct = 0

    for product in word_dict:
        curWords = len(word_dict[product])

        if curWords > maxWords:
            maxWords = curWords
            maxWordProduct = product

    return sorted(word_dict[maxWordProduct])

def parse_file_and_call_function():
    with open("TreasonsIN.txt", "r") as f:
        line = f.readline()
        test_cases = line.split('|')
        for test_case in test_cases:
            test_case = [s for s in test_case.split(' ') if len(s.strip()) > 0]
            if len(test_case) > 0:
                ans = anagram_tester(test_case)
                print '[{}]'.format(', '.join(ans))


if __name__ == "__main__":
    parse_file_and_call_function()
