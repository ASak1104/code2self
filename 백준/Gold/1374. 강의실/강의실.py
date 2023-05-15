from heapq import *

n = int(input())
heap = [tuple(map(int, input().split()))[1:] for _ in range(n)]
heapify(heap)

q = [heappop(heap)[1]]
while heap:
    s, e = heappop(heap)
    if q[0] <= s:
        heappop(q)
    heappush(q, e)
print(len(q))