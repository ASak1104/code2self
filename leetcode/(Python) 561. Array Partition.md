## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [561. Array Partition](https://leetcode.com/problems/array-partition/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import *


class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        res = 0
        nums.sort()
        for i in range(0, len(nums), 2):
            res += nums[i]
        return res
```

[//]: # (<br>)

[//]: # (### 해설)
