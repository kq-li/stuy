#! /usr/bin/python

fib_table = [0, 1]

i = 2
cur = fib_table[i - 2] + fib_table[i - 1]
while (cur < 4000000):
  fib_table.append(cur)
  i += 1
  cur = fib_table[i - 2] + fib_table[i - 1]

sum = 0
for i in fib_table:
  if i % 2 == 0:
    sum += i

print sum

    
