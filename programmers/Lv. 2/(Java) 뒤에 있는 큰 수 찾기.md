## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [뒤에 있는 큰 수 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/154539)
- 등급: Lv. 2
- 분류: 연습문제

<br>

## 코드 - Java

```java
class Solution {
    
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        int localMax = 0;
        for (int i = answer.length - 1; i > -1; i--) {
            if (numbers[i] >= localMax) {
                localMax = numbers[i];
                answer[i] = -1;
                continue;
            }
            for (int j = i + 1; answer[i] == 0; j++) {
                if (numbers[i] < numbers[j]) {
                    answer[i] = numbers[j];
                } else if (numbers[i] < answer[j]) {
                    answer[i] = answer[j];
                }
            }
        }
        return answer;
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
