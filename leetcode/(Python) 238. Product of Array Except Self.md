## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
- 등급: Medium

<br>

## 코드 - Python

```python
from typing import *


class Solution:
    
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        prod_left = [1] * len(nums)
        prod_right = prod_left[:]

        for i in range(1, len(nums)):
            prod_left[i] *= prod_left[i - 1] * nums[i - 1]
            prod_right[len(nums) - i - 1] *= prod_right[len(nums) - i] * nums[len(nums) - i]

        return [pl * pr for pl, pr in zip(prod_left, prod_right)]
```

[//]: # (<br>)

[//]: # (### 해설)
