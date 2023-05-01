## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [4. Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
- 등급: Hard

<br>

## 코드 - Python

```python
from typing import *
import statistics


class Solution:
    
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        return statistics.median(nums1 + nums2)
```

```python
# 추가 예정
```

[//]: # (<br>)

[//]: # (### 해설)
