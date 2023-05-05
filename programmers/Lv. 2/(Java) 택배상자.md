## 문제

- 출처: [ 프로그래머스 ](https://school.programmers.co.kr/)
- 문제: [ 택배상자 ](https://school.programmers.co.kr/learn/courses/30/lessons/131704)
- 등급: Lv. 2
- 분류: 연습문제

<br>

## 코드 - Java

``` java
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int odx = 0;
        int box = 1;
        while (box <= order.length || !stack.empty()) {
            int need = order[odx];
            if (box == need) {
                box++;
                odx++;
                answer++;
            } else if (!stack.isEmpty() && stack.peek() == need) {
                set.remove(stack.pop());
                odx++;
                answer++;
            } else if (!set.contains(need)) {
                stack.push(box);
                set.add(box);
                box++;
            } else {
                return answer;
            }
        }
        return answer;
    }
}
```

[//]: # (<br>)

[//]: # (## 해설)
