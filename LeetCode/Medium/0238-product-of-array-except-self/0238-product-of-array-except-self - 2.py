class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        result  = []
        temp = 1
        for i in range(len(nums)):
            result.append(temp)
            temp *= nums[i]
        temp = 1
        for i in range(len(nums)):
            result[-(i + 1)] *= temp
            temp *= nums[-(i + 1)]
        return result