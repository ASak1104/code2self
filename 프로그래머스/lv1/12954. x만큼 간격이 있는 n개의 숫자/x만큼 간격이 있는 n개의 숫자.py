def solution(x, n):
    return x and list(range(x, x * (n + 1), x)) or [0] * n