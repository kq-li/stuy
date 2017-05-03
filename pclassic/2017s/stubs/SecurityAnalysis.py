# Fill out this method
# @param letter denotes the letter that the two longest substrings of the two passwords should start on
# @param s1 denotes the first password
# @param s2 denotes the second password
def longest_common_substring (letter, s1, s2):
	return ""

# Do not modify this method
def parse_file_and_call_function():
	with open ("SecurityAnalysisIN.txt", "r") as f:
		while True:
			letter = f.readline().strip()
			s1 = f.readline().strip()
			s2 = f.readline().strip()
			if letter == '' or s1 == '' or s2 == '':
				break
			print longest_common_substring(letter, s1, s2)

if __name__ == '__main__':
    parse_file_and_call_function()