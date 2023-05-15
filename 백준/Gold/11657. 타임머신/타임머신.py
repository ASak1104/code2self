from sys import stdin, exit

N, M = map(int, stdin.readline().split())
INF = float('inf')
dist = [0] + [INF] * (N - 1)

edges = {edge: [] for edge in range(N)}
for _ in range(M):
    A, B, C = map(int, stdin.readline().split())
    edges[A - 1].append((B - 1, C))

for _ in range(N - 1):
    for v0, arr in edges.items():
        if dist[v0] is INF:
            continue
        for v1, cost in arr:
            dist[v1] = min(dist[v1], dist[v0] + cost)

for v0, arr in edges.items():
    for v1, cost in arr:
        if dist[v1] > dist[v0] + cost:
            print(-1)
            exit(0)

for i in range(1, N):
    if dist[i] is INF:
        dist[i] = -1
    print(dist[i])