# from heapq import *

# def solution(routes):
#     ans = 0
#     pre_end = -30001
#     h = [route[::-1] for route in routes]
#     heapify(h)
#     while h:
#         e, s = heappop(h)
#         if s <= pre_end:
#             continue
#         pre_end = e
#         ans += 1
#     return ans


def solution(routes):
    ans = 0
    pre_end = -30001
    for s, e in sorted(routes, key=lambda x: x[1]):
        if s <= pre_end:
            continue
        pre_end = e
        ans += 1
    return ans