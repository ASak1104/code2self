import sys

sys.setrecursionlimit(100000)

def solution(a, b, n):
    # (a - b) 개 씩 받는 전략
    return max(n - b, 0) // (a - b) * b
    # if n < a:
    #     return 0
    # d, r = divmod(n, a)
    # return d * b + solution(a, b, d * b + r)