#! /usr/bin/python

data = [[int(j) for j in i.split(',')] for i in open('data.csv', 'r').read().split('\n')[1:]]
timeStdDev = 37.4
halfTimeStdDev = timeStdDev / 2

highMileageLimit = 12000
medMileageLimit = 9500
lowMileageLimit = 7000

highTimeLimit = 300
medTimeLimit = 255
lowTimeLimit = 210

peopleTable = [[0 for j in range(3)] for i in range(3)]
percentTable = [[0 for j in range(3)] for i in range(3)]
totalPeople = 0

stdDevMultipliers = [0.005, 0.017, 0.044, 0.092, 0.15, 0.191,
                     0.191, 0.15, 0.092, 0.044, 0.017, 0.005]



for point in data:
  totalPeople += point[1]
  
  for i in range(12):
    time = point[2] - (i - 5.5) * halfTimeStdDev

    if (time > highTimeLimit):
      if (point[0] > highMileageLimit):
        peopleTable[2][2] += stdDevMultipliers[i] * point[1]
      elif (point[0] > medMileageLimit):
        peopleTable[2][1] += stdDevMultipliers[i] * point[1]
      else:
        peopleTable[2][0] += stdDevMultipliers[i] * point[1]
    elif (time > medTimeLimit):
      if (point[0] > highMileageLimit):
        peopleTable[1][2] += stdDevMultipliers[i] * point[1]
      elif (point[0] > medMileageLimit):
        peopleTable[1][1] += stdDevMultipliers[i] * point[1]
      else:
        peopleTable[1][0] += stdDevMultipliers[i] * point[1]
    else:
      if (point[0] > highMileageLimit):
        peopleTable[0][2] += stdDevMultipliers[i] * point[1]
      elif (point[0] > medMileageLimit):
        peopleTable[0][1] += stdDevMultipliers[i] * point[1]
      else:
        peopleTable[0][0] += stdDevMultipliers[i] * point[1]

for i in range(3):
  ret = ''
  for j in range(3):
    percentTable[i][j] = round(peopleTable[i][j] / totalPeople * 10000) / 100
    ret += str(percentTable[i][j]) + '  '
  print ret

    
  
      
    
      
