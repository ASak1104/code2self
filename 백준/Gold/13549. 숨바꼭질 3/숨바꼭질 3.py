import sys
from heapq import heappop, heappush

readline = sys.stdin.readline
INF = float('inf')

n, k = map(int, readline().split())

if n >= k:
    print(n - k)
    exit(0)

dists = [INF for _ in range(min(k << 1, 100_001))]
dists[n] = 0
heap = [(0, n)]

while dists[k] == INF:
    d, u = heappop(heap)

    for (dpc, v) in [(d, u << 1), (d + 1, u + 1), (d + 1, u - 1)]:
        if 0 <= v < len(dists) and dpc < dists[v]:
            dists[v] = dpc
            heappush(heap, (dpc, v))

print(dists[k])