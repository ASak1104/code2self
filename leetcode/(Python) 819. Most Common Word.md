## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [819. Most Common Word](https://leetcode.com/problems/most-common-word/)
- 등급: Easy

<br>

## 코드 - Python

```python
from typing import *
from collections import Counter
import re


class Solution:
    
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        word_dict = Counter(word for word in re.sub('[^\w]', ' ', paragraph.lower()).split() if word not in banned)
        return word_dict.most_common(1)[0][0]
```

```python
from typing import *
from collections import Counter
import re


class Solution:
    
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        str_counter = Counter(re.split(r'\W+', paragraph.lower()))
        for _ in range(len(str_counter)):
            str_most = str_counter.most_common(1)[0][0]
            if str_most in banned:
                str_counter[str_most] = -1
            else:
                return str_most
```

[//]: # (<br>)

[//]: # (### 해설)
