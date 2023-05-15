class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        res = 0
        nums.sort()
        for i in range(0, len(nums), 2):
            res += nums[i]
        return res