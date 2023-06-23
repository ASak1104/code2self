import sys
from collections import deque

input = sys.stdin.readline

n = int(input())
histogram = [int(input()) for _ in range(n)]

res = 0
stack = deque()

for e in range(n):
    while stack and histogram[stack[-1]] > histogram[e]:
        x = stack.pop()
        s = stack[-1] + 1 if stack else 0

        res = max(res, (e - s) * histogram[x])

    stack.append(e)

while stack:
    x = stack.pop()
    s = stack[-1] + 1 if stack else 0

    res = max(res, (n - s) * histogram[x])

print(res)