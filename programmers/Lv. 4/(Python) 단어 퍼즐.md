## 문제

- 출처: [ 프로그래머스 ](https://school.programmers.co.kr/)
- 문제: [ 단어 퍼즐 ](https://school.programmers.co.kr/learn/courses/30/lessons/12983)
- 등급: Lv. 4
- 분류: 2017 팁스타운

<br>

## 코드 - Python

``` python
def solution(strs, t):
    INF = float('inf')
    str_set = set(strs)
    memo = [0] + [INF] * len(t)
    for i in range(1, len(t) + 1):
        for k in range(1, 6):
            s = max(0, i - k)
            if t[s: i] in str_set:
                memo[i] = min(memo[i], memo[i - k] + 1)
    if memo[-1] is INF:
        memo[-1] = -1
    return memo[-1]
```

[//]: # (<br>)

[//]: # (## 해설)
