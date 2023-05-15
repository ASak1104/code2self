def solution(strs, t):
    INF = float('inf')
    str_set = set(strs)
    memo = [0] + [INF] * len(t)
    for i in range(1, len(t) + 1):
        for k in range(1, 6):
            s = max(0, i - k)
            if t[s: i] in str_set:
                memo[i] = min(memo[i], memo[i - k] + 1)
    if memo[-1] is INF:
        memo[-1] = -1
    return memo[-1]