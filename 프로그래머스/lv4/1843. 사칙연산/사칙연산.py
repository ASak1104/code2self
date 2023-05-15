def solution(arr):
    N = len(arr)
    M = (N >> 1) + 1
    INF = float('inf')
    for i, e in enumerate(arr):
        if i % 2 == 0:
            arr[i] = int(e)
    
    memo_max = [[-INF] * M for _ in range(M)]
    memo_min = [[INF] * M for _ in range(M)]

    for i in range(M):
        memo_max[i][i] = arr[i * 2]
        memo_min[i][i] = arr[i * 2]

    for c in range(1, M):
        for i in range(M - c):
            j = i + c
            for k in range(i, j):
                if arr[k * 2 + 1] == '+':
                    memo_max[i][j] = max(memo_max[i][j], memo_max[i][k] + memo_max[k + 1][j])
                    memo_min[i][j] = min(memo_min[i][j], memo_min[i][k] + memo_min[k + 1][j])
                else:
                    memo_max[i][j] = max(memo_max[i][j], memo_max[i][k] - memo_min[k + 1][j])
                    memo_min[i][j] = min(memo_min[i][j], memo_min[i][k] - memo_max[k + 1][j])
    return memo_max[0][-1]