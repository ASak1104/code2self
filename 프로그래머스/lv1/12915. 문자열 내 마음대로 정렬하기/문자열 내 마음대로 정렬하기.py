def solution(s, n):
    return sorted(sorted(s), key = lambda x : x[n])