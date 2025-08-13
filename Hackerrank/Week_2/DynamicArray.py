import math
import os
import random
import re
import sys

#
# Complete the 'dynamicArray' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts following parameters:
#  1. INTEGER n
#  2. 2D_INTEGER_ARRAY queries
#

def dynamicArray(n, queries):
    last_answer = 0
    seq = [[] for _ in range(n)]
    results = []
    
    for q in queries:
        op, x, y = q
        idx = ((x ^ last_answer) % n)
        
        if op == 1:
            seq[idx].append (y)
        elif op == 2:
            s_len = len (seq[idx])
            last_answer = seq[idx][y % s_len]
            results.append(last_answer)
    return results
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    q = int(first_multiple_input[1])

    queries = []

    for _ in range(q):
        queries.append(list(map(int, input().rstrip().split())))

    result = dynamicArray(n, queries)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

    fptr.close()