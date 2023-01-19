def solution(n, s, a, b, fares):
    edges = [[float('inf')] * n for _ in range(n)]
    for n1, n2, cost in fares:
        edges[n1 - 1][n2 - 1] = cost
        edges[n2 - 1][n1 - 1] = cost
    edges = floydWarshall(edges)
    cost = edges[s - 1][a - 1] + edges[s - 1][b - 1]
    for node in range(n):
        if node != s - 1:
            cost = min(cost, edges[s - 1][node] + edges[node][a - 1] + edges[node][b - 1])
    return cost


def floydWarshall(base):
    d = base[:]
    for k in range(len(base)):
        for i in range(len(base)):
            for j in range(len(base)):
                if d[i][j] > d[i][k] + d[k][j]:
                    d[i][j] = d[i][k] + d[k][j]
    return d


s1 = solution(6, 4, 6, 2, [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]])
print(s1)
s2 = solution(7, 3, 4, 1, [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]])
print(s2)
s3 = solution(6, 4, 5, 6, [[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]])
print(s3)
