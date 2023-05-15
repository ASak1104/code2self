def solution(n, cnt=0):
    if n < 3:
        return cnt + 1
    return solution(n >> 1, cnt + (n % 2))