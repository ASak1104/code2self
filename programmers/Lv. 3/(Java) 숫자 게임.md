## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [숫자 게임](https://school.programmers.co.kr/learn/courses/30/lessons/12987)
- 등급: Lv. 3
- 분류: Summer/Winter Coding(~2018)

<br>

## 코드 - Java

```java
import java.util.Arrays;

class Solution {

    public int solution(int[] A, int[] B) {
        descSort(A);
        descSort(B);

        int answer = 0;
        int j = 0;
        for (int a : A) {
            if (a < B[j]) {
                answer++;
                j++;
            }
        }
        return answer;
    }

    private void descSort(int[] arr) {
        Arrays.sort(arr);
        int temp = 0;
        for (int i = 0; i < arr.length >> 1; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
