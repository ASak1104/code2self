from collections import Counter as ct
from itertools import combinations as cb
from bisect import insort_right


def solution(orders, course):
    res = []
    for n in course:
        cntr = ct()
        orders = list(filter(lambda x: len(x) >= n, orders))
        for order in orders:
            cntr += ct(map(''.join, cb(sorted(order), n)))
        if not cntr or cntr.most_common(1)[0][1] < 2:
            continue
        target = cntr.most_common(1)[0][1]
        for k, v in cntr.most_common():
            if v != target:
                break
            insort_right(res, k)
    return res