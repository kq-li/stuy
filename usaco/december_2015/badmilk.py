#! /usr/bin/python

fin = open('badmilk.in', 'r')
fout = open('badmilk.out', 'w+')

s = fin.read().split('\n')
first = s[0].split(' ')
numPeople = int(first[0])
numMilks = int(first[1])
numDrinks = int(first[2])
numSick = int(first[3])

drinks = {}
sick = {}

for i in s[1:1 + numDrinks]:
  i = map(int, i.split(' '))
  p = i[0] - 1
  m = i[1] - 1
  t = i[2]
  if p in drinks:
    drinks[p].append((m, t))
  else:
    drinks[p] = [(m, t)]
  
for i in s[1 + numDrinks: 1 + numDrinks + numSick]:
  i = map(int, i.split(' '))
  p = i[0] - 1
  t = i[1]
  sick[p] = t

def firstDrink(person, milk):
  minTime = 0
  for drink in drinks[person]:
    if milk == drink[0]:
      if minTime == 0:
        minTime = drink[1]
      else:
        minTime = min(minTime, drink[1])
  return minTime
  
def whoDrankBeforeSick(milk):
  people = []
  for person in drinks:
    time = firstDrink(person, milk)
    if time > 0:
      if person in sick:
        if time < sick[person]:
          people.append(person)
      else:
        people.append(person)    
  return people

def whoDrank(milk):
  people = []
  for person in drinks:
    for drink in drinks[person]:
      if drink[0] == milk:
        people.append(person)
        break
  return people

def whichBad():
  badmilks = []
  for milk in range(numMilks):
    people = whoDrankBeforeSick(milk)
    allSick = True
    for person in sick:
      if person not in people:
        allSick = False
    if allSick:
      badmilks.append(milk)
  return badmilks

maxCures = 0
for badmilk in whichBad():
  maxCures = max(len(whoDrank(badmilk)), maxCures)
fout.write(str(maxCures))
fin.close()
fout.close()

  


