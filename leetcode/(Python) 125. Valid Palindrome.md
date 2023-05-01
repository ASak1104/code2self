## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
- 등급: Easy

<br>

## 코드 - Python

```python
import re


class Solution:
    
    def isPalindrome(self, s: str) -> bool:
        preprocess = re.sub('[^a-z0-9]', '', s.lower())
        return preprocess == preprocess[::-1]
```

```python
class Solution:
    
    def isPalindrome(self, s: str) -> bool:
        s_alnum = ''.join([e for e in s.lower() if e.isalnum()])
        return s_alnum == s_alnum[::-1]
```

[//]: # (<br>)

[//]: # (### 해설)
