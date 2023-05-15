def solution(triangle):
    memo = [arr[:] for arr in triangle]
    for lev in range(len(memo) - 2, -1, -1):
        for i in range(len(memo[lev])):
            memo[lev][i] += max(memo[lev + 1][i], memo[lev + 1][i + 1])
    return memo[0][0]