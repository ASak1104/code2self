import sys

input = sys.stdin.readline

V, E = map(int, input().split())
edges = []

for _ in range(E):
    u, v, c = map(int, input().split())
    edges.append((u - 1, v - 1, c))

edges.sort(key=lambda x: x[2])

sets = [{i} for i in range(V)]
weights = 0

for u, v, c in edges:
    if sets[u] is sets[v]:
        continue

    union = sets[u] | sets[v]
    weights += c

    if len(union) == V:
        break

    for node in union:
        sets[node] = union

print(weights)