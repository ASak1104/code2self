## 문제

- 출처: [백준](https://www.acmicpc.net)
- 문제: [18114번: 블랙 프라이데이](https://www.acmicpc.net/problem/18114)
- 등급: 골드 5

<br>

## 코드 - Python

```python
from sys import stdin


def solution(c, w_set):
    if c in w_set:
        return 1
    for w1 in w_set:
        if c - w1 != w1 and c - w1 in w_set:
            return 1
        for w2 in w_set:
            if w1 != w2 and c - w1 - w2 not in (w1, w2) and c - w1 - w2 in w_set:
                return 1
    return 0


_, c = map(int, stdin.readline().split())
w_set = set(map(int, stdin.readline().split()))
print(solution(c, w_set))
```


```python
from sys import stdin


def binarySearch(x, array, start=0):
    end = len(array) - 1
    while start <= end:
        mid = (start + end) >> 1
        if array[mid] == x:
            return mid
        if array[mid] < x:
            start = mid + 1
        else:
            end = mid - 1
    return -1


def solution(n, c, w_list):
    if binarySearch(c, w_list) > -1:
        return 1

    for i in range(n):
        if c - w_list[i] <= w_list[i]:
            break
        if binarySearch(c - w_list[i], w_list, start=i + 1) > -1:
            return 1
        for j in range(i + 1, n):
            if c - w_list[i] - w_list[j] <= w_list[j]:
                break
            if binarySearch(c - w_list[i] - w_list[j], w_list, start=j + 1) > -1:
                return 1
    return 0


n, c = map(int, stdin.readline().split())
w_list = sorted(map(int, stdin.readline().split()))
print(solution(n, c, w_list))
```

[//]: # (<br>)

[//]: # (### 해설)
