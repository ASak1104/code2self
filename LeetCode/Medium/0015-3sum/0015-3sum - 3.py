from typing import *
from collections import Counter
from itertools import combinations


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums_cntr = Counter(nums)
        combi2 = set(combinations(nums, 2))
        
        answer = set()
        for tup in combi2:
            if nums_cntr[-sum(tup)] > tup.count(-sum(tup)):
                answer.add(tuple(sorted(list(tup) + [-sum(tup)])))
        return set(answer)