## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [1. Two Sum](https://leetcode.com/problems/two-sum/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import *


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_counter = Counter(nums)
        for k, v in num_counter.items():
            if k * 2 == target:
                if v == 2:
                    return [i for i in range(len(nums)) if nums[i] == k]
            elif target - k in num_counter:
                return [nums.index(k), nums.index(target - k)]
```


[//]: # (<br>)

[//]: # (### 해설)
