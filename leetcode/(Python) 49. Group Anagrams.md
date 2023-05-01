## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)
- 등급: Medium

<br>

## 코드 - Python

```python
from typing import *
from collections import defaultdict


class Solution:
    
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagrams = defaultdict(list)
        for word in strs:
            word_sorted = tuple(sorted(word))
            anagrams[word_sorted].append(word)
        return anagrams.values()
```


```python
from typing import *


class Solution:
    
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        a = dict()
        for str in strs:
            key = tuple(sorted(str))
            if key in a:
                a[key].append(str)
            else:
                a[key] = [str]
        return a.values()
```

[//]: # (<br>)

[//]: # (### 해설)
