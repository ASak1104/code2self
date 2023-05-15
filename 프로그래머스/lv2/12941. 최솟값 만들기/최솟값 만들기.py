from heapq import *


def solution(A, B):
    a_min, b_max = [], []
    for a, b in zip(A, B):
        heappush(a_min, a)
        heappush(b_max, -b)
    res = 0
    while a_min:
        res -= heappop(a_min) * heappop(b_max)
    return res