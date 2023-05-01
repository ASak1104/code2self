## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import *


class Solution:
    right_bracket_map: Dict = {
        ')': '(',
        '}': '{',
        ']': '[',
    }

    def isValid(self, s: str) -> bool:
        stack = []
        for bracket in s:
            if bracket in self.right_bracket_map:
                if not stack or stack.pop() != self.right_bracket_map[bracket]:
                    return False
            else:
                stack.append(bracket)
        return len(stack) == 0
```

[//]: # (<br>)

[//]: # (### 해설)
