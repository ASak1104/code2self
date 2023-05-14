## 문제

- 출처: [ 프로그래머스 ](https://school.programmers.co.kr/)
- 문제: [ 올바른 괄호의 갯수 ](https://school.programmers.co.kr/learn/courses/30/lessons/12929)
- 등급: Lv. 4
- 분류: 연습문제

<br>

## 코드 - Python

``` python
memo = [1, 1, 2, 5]

def solution(n):
    if n < len(memo):
        return memo[n]
    else:
        temp = 0
        for i in range(n):
            temp += solution(i) * solution(n - 1 - i)
        memo.append(temp)
        return memo[n]
```

[//]: # (<br>)

[//]: # (## 해설)
