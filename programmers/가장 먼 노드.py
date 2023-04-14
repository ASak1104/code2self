from heapq import *


def solution(n, edge):
    dist = [float('inf')] * n
    edge_map = {i: set() for i in range(n)}
    for n1, n2 in edge:
        edge_map[n1 - 1].add(n2 - 1)
        edge_map[n2 - 1].add(n1 - 1)

    dist[0] = 0
    pq = [(0, 0)]
    while pq:
        d, n = heappop(pq)
        for v in edge_map[n]:
            if d + 1 < dist[v]:
                dist[v] = d + 1
                heappush(pq, (d + 1, v))
    return dist.count(max(dist))
