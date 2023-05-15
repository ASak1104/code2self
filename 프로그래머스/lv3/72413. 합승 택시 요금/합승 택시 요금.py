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
    for i in range(len(base)):
        d[i][i] = 0
    return d