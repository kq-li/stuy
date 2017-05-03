
## This is for you to fill out. 
# @param(num) is the upper bound of the domain, [1, n]
# You want to return the number of solutions to the equation ab - cd = 1
def get_solution(num):
	count = 0
	return count

"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
	with open('CardinalConfusionIN.txt') as f:
		nums = f.read().split("\n")
		for num in nums:
			if num != '':
				n = int(num)
				count = get_solution(n)
				print (count)

if __name__ == "__main__":
    parse_file_and_call_function()