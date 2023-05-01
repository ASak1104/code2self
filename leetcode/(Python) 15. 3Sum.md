## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [15. 3Sum](https://leetcode.com/problems/3sum/)
- 등급: Medium

<br>

## 코드 - Python

```python
from typing import *
from collections import Counter


class Solution:

    def threeSum(self, nums: List[int]) -> List[List[int]]:
        res_set = set()
        num_map = Counter(nums)
        items = num_map.items()
        for k, v in items:
            if k == 0:
                if num_map[k] > 2:
                    res_set.add((0, 0, 0))
                continue

            if v > 1 and -(k * 2) in num_map:
                res_set.add(tuple(sorted([k, k, -(k * 2)])))

            for ko, vo in items:
                target = -(k + ko)
                if k == ko or target in (k, ko):
                    continue
                if target in num_map:
                    res_set.add(tuple(sorted([k, ko, target])))

        return [list(e) for e in res_set]
```

```python
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
```

[//]: # (<br>)

[//]: # (### 해설)
