## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [쿼드압축 후 개수 세기](https://school.programmers.co.kr/learn/courses/30/lessons/68936)
- 등급: Lv. 2
- 분류: 월간 코드 챌린지 시즌1

<br>

## 코드 - Java

``` java
import java.util.Deque;
import java.util.ArrayDeque;

class Solution {

    public int[] solution(int[][] arr) {
        int[] answer = {0, 0};
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(new Node(0, 0, arr.length));
        while (!deque.isEmpty()) {
            Node node = deque.poll();
            int target = arr[node.rs][node.cs];
            if (node.k == 1) {
                answer[target]++;
                continue;
            }
            boolean compressible = true;
            for (int r = node.rs; r < node.rs + node.k; r++) {
                for (int c = node.cs; c < node.cs + node.k; c++) {
                    if (arr[r][c] != target) {
                        compressible = false;
                        break;
                    }
                }
            }
            if (compressible) {
                answer[target]++;
            } else {
                int k = node.k >> 1;
                for (int r = node.rs; r < node.rs + node.k; r += k) {
                    for (int c = node.cs; c < node.cs + node.k; c += k) {
                        deque.addFirst(new Node(r, c, k));
                    }
                }
            }
        }
        return answer;
    }

    private static class Node {
        int rs;
        int cs;
        int k;

        public Node(int rs, int cs, int k) {
            this.rs = rs;
            this.cs = cs;
            this.k = k;
        }
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
