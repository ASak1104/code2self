from sys import stdin
from heapq import *

N = int(stdin.readline())
space = [[] for _ in range(N)]

start = None
for r in range(N):
    for c, e in enumerate(stdin.readline().split()):
        if e == '9':
            start = (0, r, c)
            e = '0'
        space[r].append(int(e))

moves = [(-1, 0), (0, -1), (0, 1), (1, 0)]
visits = [[0] * N for _ in range(N)]
visits[start[1]][start[2]] = 1

time = 0
size = 2
predation = 0
heap = [start]

while heap:
    t, r, c = heappop(heap)
    if 0 < space[r][c] < size:
        space[r][c] = 0
        time, t = time + t, 0

        predation += 1
        if size == predation:
            size, predation = size + 1, 0

        visits = [[0] * N for _ in range(N)]
        visits[r][c] = 1

        heap = []

    for r0, c0 in moves:
        r1, c1 = r + r0, c + c0
        if 0 <= r1 < N and 0 <= c1 < N:
            if not visits[r1][c1] and space[r1][c1] <= size:
                heappush(heap, (t + 1, r1, c1))
                visits[r1][c1] = 1
print(time)