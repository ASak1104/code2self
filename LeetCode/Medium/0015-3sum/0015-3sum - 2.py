from typing import *
from collections import Counter


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        res_set = set()
        num_map = Counter(nums)
        items = num_map.items()
        for k, v in items:
            if k == 0:
                if num_map[k] > 2:
                    res_set.add((0, 0, 0))
                continue

            if v > 1 and -(k * 2) in num_map:
                res_set.add(tuple(sorted([k, k, -(k * 2)])))

            for ko, vo in items:
                target = -(k + ko)
                if k == ko or target in (k, ko):
                    continue
                if target in num_map:
                    res_set.add(tuple(sorted([k, ko, target])))

        return [list(e) for e in res_set]