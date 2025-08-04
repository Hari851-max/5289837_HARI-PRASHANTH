import math
import os
import random
import re
import sys

#
# Complete the 'plusMinus' function below.
#
# The function accepts INTEGER_ARRAY arr as parameter.
#

def plusMinus(arr):
    n = len(arr)
    position = neg = zero = 0
    
    for num in arr:
        if num > 0:
            position += 1
        elif num < 0:
            neg += 1
        else:
            zero += 1
    
    
    print(f"{position/n:.6f}")
    print(f"{neg/n:.6f}")
    print(f"{zero/n:.6f}")
    
if __name__ == '__main__':
    n = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    plusMinus(arr)
