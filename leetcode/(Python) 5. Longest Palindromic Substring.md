## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
- 등급: Medium

<br>

## 코드 - Python

```python
class Solution:
    
    def longestPalindrome(self, s: str) -> str:
        if len(s) < 2 or s == s[::-1]:
            return s

        def expand(s: str, left: int, right: int) -> str:
            while left > -1 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return s[left + 1: right]

        answer = ''
        for left in range(len(s) - 1):
            answer = max(answer,
                         expand(s, left, left + 1),
                         expand(s, left, left + 2),
                         key=len)
        return answer
```

```python
class Solution:
    
    def longestPalindrome(self, s: str) -> str:
        if len(s) < 2 or s == s[::-1]:
            return s
        
        for len_pal in range(len(s), 0, -1):
            for i in range(len(s) - len_pal + 1):
                pal = s[i: i + len_pal]
                if pal == pal[::-1]:
                    return pal
```

[//]: # (<br>)

[//]: # (### 해설)
