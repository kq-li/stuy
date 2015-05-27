#! /usr/bin/python

import re

words = re.split('[^a-z0-9]*', open('hamlet.txt').read().lower())
while '' in words:
  words.remove('')
word_counts = {}
for word in words:
  if word in word_counts:
    word_counts[word] += 1
  else:
    word_counts[word] = 1
print word_counts
