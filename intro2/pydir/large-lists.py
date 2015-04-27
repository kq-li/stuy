#!/usr/bin/python

def longestPal(wordlist):
  dictall = open(wordlist, 'r').read().split('\n')
  longest = ['']
  for word in dictall:
    if word == word[::-1]:
      if len(word) > len(longest[0]):
        longest = [word]
      elif len(word) == len(longest[0]):
        longest.append(word)
  return longest if len(longest) != 1 else longest[0]

def isAnagram(w1, w2):
  return sorted(list(w1)) == sorted(list(w2))

def anagramsOf(w):
  dictall = open('dictall.txt', 'r').read().split('\n')
  anagrams = []
  for word in dictall:
    if isAnagram(word, w):
      anagrams.append(word)
  return anagrams

def uniqueify(L):
  ans = []
  for i in L:
    if i not in ans:
      ans.append(i)
  return ans

def oneLetterAway(wordlist, root):
  dictall = open(wordlist, 'r').read().split('\n')
  oLAs = []
  for word in dictall:
    if len(root) == len(word):
      diffChars = 0
      for i in range(len(root)):
        if root[i] != word[i]:
          diffChars += 1
      if diffChars == 1:
        oLAs.append(word)
  return oLAs

