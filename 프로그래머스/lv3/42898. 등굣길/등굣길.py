def solution(m, n, puddles):
    invalidSet = set()
    for c, r in puddles:
        invalidSet.add((r - 1, c - 1))
        
    dpList = [[0] * m for _ in range(n)]
    dpList[0][0] = 1
    
    for r in range(n):
        for c in range(m):
            if (r, c) in invalidSet:
                continue
            if r > 0:
                dpList[r][c] += dpList[r - 1][c]
            if c > 0:
                dpList[r][c] += dpList[r][c - 1]
    
    return dpList[-1][-1] % 1_000_000_007