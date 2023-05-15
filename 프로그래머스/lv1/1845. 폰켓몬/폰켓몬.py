from collections import Counter as cntr


def solution(nums):
    return min(len(cntr(nums)), len(nums) / 2)