## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [344. Reverse String](https://leetcode.com/problems/reverse-string/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import List


class Solution:
    
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        s.reverse()
```

```python
from typing import List


class Solution:

    def reverseString(self, s: List[str]) -> None:
        for i in range(len(s) >> 1):
            # -(i + 1) == len(s) - i - 1
            s[i], s[-(i + 1)] = s[-(i + 1)], s[i]
```

[//]: # (<br>)

[//]: # (### 해설)
