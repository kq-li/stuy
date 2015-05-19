def div7(low, high):
	count = low
	acc = 0
	while count <= high:
		if count % 7 == 0:
			acc += 1
		count += 1

	return acc
print div7(0, 2), 1
print div7(1, 50), 7
print div7(1, 14), 2
print div7(0, 0), 1
print div7(3, 3), 0
print div7(56, 119), 10
print div7(0, 100), 15
