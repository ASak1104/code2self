import sys
from math import sqrt

readline = sys.stdin.readline

n = int(readline())
stars = [tuple(map(float, readline().split(' '))) for _ in range(n)]

edges = []
for i in range(n - 1):
    for j in range(i + 1, n):
        dist = (stars[i][0] - stars[j][0]) ** 2 + (stars[i][1] - stars[j][1]) ** 2
        edges.append((dist, i, j))
edges.sort()

star_sets = [{i} for i in range(n)]
res = 0.0
for dist, u, v in edges:
    if star_sets[u] is star_sets[v]:
        continue
    res += sqrt(dist)
    union = star_sets[u].union(star_sets[v])
    if len(union) == n:
        break
    for i in union:
        star_sets[i] = union
print(res)