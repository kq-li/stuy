# This is for you to fill out
# @param matrix - a list of lists of integers
# @return a single integer denoting the number of even sum arrays
def countEvenArrays(matrix):
	count = 0
	return count

"""FILE PARSING - DO NOT MODIFY"""



def parse_file_and_call_function():
	with open("CopsRunIntoEachOtherIN.txt") as f:
		p = 0
		lines = f.read().split("\n")
		
		while p < len(lines):
			if lines[p] == '':
				break
			nums = lines[p].split(" ")
			p = p + 1
			m, n = int(nums[0]), int(nums[1])
			matrix = []

			for i in range(m):
				matrix.append([])
				for j in range(n):
					matrix[i].append(0)

			for i in range(m):
				for j in range(n):
					if lines[p] == '':
						continue
					matrix[i][j] = int(lines[p])
					p = p + 1

			print (countEvenArrays(matrix))

if __name__ == "__main__":
    parse_file_and_call_function()
	