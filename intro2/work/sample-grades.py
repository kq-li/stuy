#!/usr/bin/python

import sys
import re

def getCSVData(filename):
  rows = re.split('\r?\n', open(filename, 'r').read())
  rows = [row.split(',') for row in rows[:-1]]
  return rows

def isNumeric(val):
  try:
    float(val)
    return True
  except ValueError:
    return False

def avg(colName, classNum):
  num = columnHeaders.index(colName)
  grades = []
  for i in data:
    if i[num] != '' and i[1] == classNum:
      grades.append(float(i[num]))
  return sum(grades) / len(grades)

def studentGrade(studentNum):
  row = rows[studentNum + 2][2:-1]
  grade = 0
  for i in range(len(row)):
    if row[i] == '':
      grade = -1
      break
    grade += float(row[i]) / float(maxScores[i + 2]) * float(weight[i + 2]) * 100.0
  return grade  

def printOptions():
  print 'Options:'
  print '1. get a class mean grade for an assessment'
  print '2. get a student\'s course grade'
  print '3. exit'

rows = getCSVData('sample-grades.csv')
columnHeaders = rows[0]
maxScores = rows[1]
weight = rows[2]
data = [[float(j) if isNumeric(j) else j for j in i] for i in rows[3:]]

print '=========================='
print '||||| GRADE DATABASE |||||'
print '==========================\n'

printOptions()
option = '0'

while option != '3':
  option = raw_input('Choose an option [1-3]: ')

  if option == '1':
    classNum, test = 0, ''
    while classNum not in ['1', '2', '3', '4']:
      classNum = raw_input('Choose a class [1-4]: ')
    while test not in columnHeaders[2:-1]:
      test = raw_input('Choose a test [Quiz 1, Test 1, Quiz 2, Test 2, Project, Final ]: ')
    print 'The %s average for class %s is %s\n' % (test, classNum, avg(test, int(classNum)))
    printOptions()
    
  elif option == '2':
    student = 0
    while student not in [str(i) for i in range(1, 122)]:
      student = raw_input('Choose a student [1-122]: ')
    print 'Student %s course grade is %s\n' % (student, studentGrade(int(student)))
    printOptions()
    
print '\nBye.'



## Command line argument form, with different operations

## def getVal(row, col):
##   val = rows[int(row) - 1][int(col) - 1]
##   return float(val) if isNumeric(val) else val

## def mode(colName, classNum):
##   num = columnHeaders.index(colName)
##   grades = []
##   for i in data:
##     if i[num] != '' and i[1] == classNum:
##       grades.append(float(i[num]))
##   counts = [grades.count(i) for i in grades]
##   return grades[counts.index(max(counts))]

## def med(colName, classNum):
##   num = columnHeaders.index(colName)
##   grades = []
##   for i in data:
##     if i[num] != '' and i[1] == classNum:
##       grades.append(float(i[num]))
##   return grades[(len(grades) - 1) / 2]

## ops = {'average': avg, 'mode': mode, 'median': med, 'get': getVal}

## if len(sys.argv) != 4:
##   print 'Usage: ./sample-grades.py <operation> <arg1> <arg2>'
##   exit(1)

## op = sys.argv[1]
## arg = sys.argv[2:]

## if op in ops:
##   print ops[op](arg[0], arg[1])
## else:
##   print 'Invalid operator: op must be in ' + str(ops.keys())

