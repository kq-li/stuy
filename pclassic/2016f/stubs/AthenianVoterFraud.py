def athenian_voter_fraud(votes):
    """

    :param votes: list containing the identifier for every ballot which was submitted
    :return: list of identifiers of voters who voted more than once
    """

    # TODO: implement
    return votes


def parse_file_and_call_function():
    with open("AthenianVoterFraudIN.txt", "r") as f:
        for line in f:
            votes = [int(vote) for vote in line.split(',')]
            print ','.join(
                [str(vote) for vote in sorted(athenian_voter_fraud(votes))])


if __name__ == "__main__":
    parse_file_and_call_function()
