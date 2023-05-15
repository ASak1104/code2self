from heapq import *

n = int(input())
heap = [tuple(map(int, input().split()))[::-1] for _ in range(n)]
heapify(heap)

ans = 0
while heap:
    ans += 1
    end = heappop(heap)[0]
    while heap and heap[0][1] < end:
        heappop(heap)
print(ans)