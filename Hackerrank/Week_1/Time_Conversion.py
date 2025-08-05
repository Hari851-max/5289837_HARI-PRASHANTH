import math
import os
import random
import re
import sys

#
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def timeConversion(s):
    time_part = s[:-2]
    am_pm = s[-2:]
    
    hours = int(time_part[:2])
    minutes = time_part[3:5]
    seconds = time_part[6:]
    
    if am_pm =="PM":
        if hours != 12:
            hours += 12
    elif am_pm == "AM":
         if hours == 12:
            hours = 0
    return f"{hours:02d}:{minutes}:{seconds}"

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = timeConversion(s)

    fptr.write(result + '\n')

    fptr.close()
