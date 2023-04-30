## 문제

- 출처: [백준](https://www.acmicpc.net)
- 문제: [1026번: 보물](https://www.acmicpc.net/problem/1026)
- 등급: 실버 4

<br>

## 코드 - Python

```python
from sys import stdin

n = int(stdin.readline())
a = list(map(int, stdin.readline().split()))
b = list(map(int, stdin.readline().split()))

answer = 0
for e1, e2 in zip(sorted(a), sorted(b, reverse=True)):
    answer += e1 * e2
print(answer)
```

[//]: # (<br>)

[//]: # (### 해설)
