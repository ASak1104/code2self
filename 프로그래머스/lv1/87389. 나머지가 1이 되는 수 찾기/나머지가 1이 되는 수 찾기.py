def solution(n):
    if n % 2:
        return 2
    res = n - 1
    for x in range(n - 3, 2, -2):
        if n % x == 1:
            res = x
    return res