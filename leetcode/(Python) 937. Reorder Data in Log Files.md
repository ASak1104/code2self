## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [937. Reorder Data in Log Files](https://leetcode.com/problems/reorder-data-in-log-files/)
- 등급: Medium

<br>

## 코드 - Python

```python
from typing import *


class Solution:
    
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        list_letter, list_digit = [], []
        for s in logs:
            if any(c.isdigit() for c in s[s.find(' '):]):
                list_digit.append(s)
            else:
                list_letter.append(s)
        list_letter.sort(key=lambda x: (x[x.find(' '):], x[:x.find(' ')]))
        return list_letter + list_digit
```

```python
from typing import *


class Solution:
    
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        letters, digits = [], []
        for s in logs:
            if s[s.find(' ') + 1].isdigit():
                digits.append(s)
            else:
                letters.append(s)
        letters.sort(key=lambda x: (x[x.find(' '):], x[:x.find(' ')]))
        return letters + digits
```

[//]: # (<br>)

[//]: # (### 해설)
