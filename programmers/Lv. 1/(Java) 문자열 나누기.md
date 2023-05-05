## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [문자열 나누기](https://school.programmers.co.kr/learn/courses/30/lessons/140108)
- 등급: Lv. 1
- 분류: 연습문제

<br>

## 코드 - Java

```java
class Solution {

    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length();) {
            char target = s.charAt(i++);
            int tCnt = 1;
            while (tCnt != 0 && i < s.length()) {
                tCnt += s.charAt(i++) == target ? 1 : -1;
            }
            answer++;
        }
        return answer;
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
