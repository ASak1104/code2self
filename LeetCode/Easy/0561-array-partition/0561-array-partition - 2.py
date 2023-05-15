class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        nums_sorted = sorted(nums)
        return sum(nums_sorted[i] for i in range(0, len(nums), 2))