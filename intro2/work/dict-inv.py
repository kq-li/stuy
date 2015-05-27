#! /usr/bin/python

import re

def countWords(filename):
  words = re.split('[^a-z0-9]*', open(filename).read().lower())
  while '' in words:
    words.remove('')
  word_counts = {}
  for word in words:
    if word in word_counts:
      word_counts[word] += 1
    else:
      word_counts[word] = 1
  return word_counts

def countDigrams(filename):
  
  words = re.split('[^a-z0-9\']*', open(filename).read().lower())
  while '' in words:
    words.remove('')
  digrams = [(words[i] + ' ' + words[i + 1]) for i in range(0, len(words) - 1, 2)]
  digram_counts = {}
  for digram in digrams:
    if digram in digram_counts:
      digram_counts[digram] += 1
    else:
      digram_counts[digram] = 1
  return digram_counts

def invertDict(dic):
  inverted = {}
  for key in dic:
    if dic[key] in inverted:
      inverted[dic[key]] += [key]
    else:
      inverted[dic[key]] = [key]
  return inverted

count = invertDict(countDigrams('hamlet.txt'))
print max(count.keys()), count[max(count.keys())]
