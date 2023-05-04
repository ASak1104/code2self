## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [2 x n 타일링](https://school.programmers.co.kr/learn/courses/30/lessons/12900)
- 등급: Lv. 2
- 분류: 연습문제

<br>

## 코드 - Java

```java
class Solution {
    
    public int solution(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int k = 2; k <= n; k++) {
            memo[k] = (memo[k - 1] + memo[k - 2]) % 1_000_000_007;
        }
        return memo[n];
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
