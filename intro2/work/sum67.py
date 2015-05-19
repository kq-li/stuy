def sum67nofind(nums):
    acc = 0
    isSix = False
    for i in nums:
        if isSix:
            isSix = i != 7
        else:
            if i == 6:
                isSix = True
            else:
                acc += i
    return acc

def sum67find(nums):
  while 6 in nums:
    nums[nums.index(6):nums[nums.index(6):].index(7)+len(nums[:nums.index(6)])+1] = []
  return sum(nums)
