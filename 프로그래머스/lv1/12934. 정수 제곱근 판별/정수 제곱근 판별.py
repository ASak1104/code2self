def solution(n):
    if n ** 0.5 == round(n ** 0.5, 1):
        return int(n ** 0.5 + 1) ** 2
    return -1