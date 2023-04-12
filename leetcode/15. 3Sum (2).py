from typing import *


class Solution:

    def threeSum(self, nums: List[int]) -> List[List[int]]:
        res = []
        nums.sort()
        for i in range(len(nums) - 2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            left, right = i + 1, len(nums) - 1
            while left < right:
                tsum = nums[i] + nums[left] + nums[right]
                if tsum != 0:
                    left += int(tsum < 0)
                    right -= int(tsum > 0)
                    continue

                res.append([nums[i], nums[left], nums[right]])

                while left < right and nums[left] == nums[left + 1]:
                    left += 1
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1
                left += 1
                right -= 1

        return res


s = Solution()
a = s.threeSum(
    [-1, 0, 1, 2, -1, -4]
)
print(a)
