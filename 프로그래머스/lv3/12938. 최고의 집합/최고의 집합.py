def solution(n, s):
    if s < n:
        return [-1]
    d, m = divmod(s, n)
    return [d + 1 if i < m else d for i in range(n)][::-1]