def solution(n):
    pprev, prev = 0, 1
    for i in range(2, n):
        pprev, prev = prev, pprev + prev
    return (pprev + prev) % 1234567