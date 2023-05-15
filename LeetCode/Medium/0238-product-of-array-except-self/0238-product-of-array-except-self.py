class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        prod_left = [1] * len(nums)
        prod_right = prod_left[:]

        for i in range(1, len(nums)):
            prod_left[i] *= prod_left[i - 1] * nums[i - 1]
            prod_right[len(nums) - i - 1] *= prod_right[len(nums) - i] * nums[len(nums) - i]

        return [pl * pr for pl, pr in zip(prod_left, prod_right)]