import sys
from itertools import combinations as cb

readline = sys.stdin.readline

s, k = map(int, readline().split())
seq = list(map(int, readline().split()))

res = seq.count(k)
for i in range(2, s + 1):
    res += list(map(sum, cb(seq, i))).count(k)
print(res)