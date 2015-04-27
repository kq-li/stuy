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

def getVal(row, col):
  val = rows[int(row) - 1][int(col) - 1]
  return float(val) if isNumeric(val) else val

def avg(colName, classNum):
  num = columnHeaders.index(colName)
  grades = []
  for i in data:
    if i[num] != '' and i[1] == classNum:
      grades.append(float(i[num]))
  return sum(grades) / len(grades)

def mode(colName, classNum):
  num = columnHeaders.index(colName)
  grades = []
  for i in data:
    if i[num] != '' and i[1] == classNum:
      grades.append(float(i[num]))
  counts = [grades.count(i) for i in grades]
  return grades[counts.index(max(counts))]

def med(colName, classNum):
  num = columnHeaders.index(colName)
  grades = []
  for i in data:
    if i[num] != '' and i[1] == classNum:
      grades.append(float(i[num]))
  return grades[(len(grades) - 1) / 2]

def studentGrade(studentNum):
  row = rows[studentNum + 2][2:-1]
  grade = 0
  for i in range(len(row)):
    if row[i] == '':
      grade = -1
      break
    grade += float(row[i]) / float(maxScores[i + 2]) * float(weight[i + 2]) * 100.0
  return grade  

rows = getCSVData('sample-grades.csv')
columnHeaders = rows[0]
maxScores = rows[1]
weight = rows[2]
data = rows[3:]


ops = {'average': avg, 'mode': mode, 'median': med, 'get': getVal}

if len(sys.argv) != 4:
  print 'Usage: ./sample-grades.py <operation> <arg1> <arg2>'
  exit(1)

op = sys.argv[1]
arg = sys.argv[2:]

if op in ops:
  print ops[op](arg[0], arg[1])
else:
  print 'Invalid operator: op must be in ' + str(ops.keys())

