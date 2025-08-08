import math
import os
import random
import re
import sys
import string

#
# Complete the 'pangrams' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def pangrams(s):
      s = s.lower()
      alphabet_set = set(string.ascii_lowercase)
      
      input_set = set(c for c in s if 'a' <= c <= 'z')
      
      if alphabet_set.issubset(input_set):
        return "pangram"
      else:
        return "not pangram"
        
        
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = pangrams(s)

    fptr.write(result + '\n')

    fptr.close()