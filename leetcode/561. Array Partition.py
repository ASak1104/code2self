from typing import *


class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        res = 0
        nums.sort()
        for i in range(0, len(nums), 2):
            res += nums[i]
        return res


s = Solution()
a = s.arrayPairSum(
    [1, 4, 3, 2]
)
print(a)
