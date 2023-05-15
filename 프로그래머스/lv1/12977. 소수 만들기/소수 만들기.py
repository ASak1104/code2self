from itertools import combinations as cb
from collections import Counter as ct


def solution(nums):
    cb_nums = ct(map(sum, cb(nums, 3)))
    max_num = max(cb_nums.keys())
    pri_nums = set(range(2, max_num + 1))
    for i in range(2, int(max_num ** 0.5) + 1):
        pri_nums -= set(range(i ** 2, max_num + 1, i))
    return sum([v for k, v in cb_nums.items() if k in pri_nums])