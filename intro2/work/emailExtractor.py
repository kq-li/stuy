import re

def emailExtractor(s):
    match = re.findall(r'\w+(?:\w|\.)*@\w+(?:\.\w+)+', s)
    if match:
        print match
    else:
        print "None found."
