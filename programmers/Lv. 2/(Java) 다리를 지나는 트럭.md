## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [다리를 지나는 트럭](https://school.programmers.co.kr/learn/courses/30/lessons/42583)
- 등급: Lv. 2
- 분류: 스택/큐

<br>

## 코드 - Java

```java
import java.util.Deque;
import java.util.ArrayDeque;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Bridge bridge = new Bridge(bridge_length, weight);
        for (int i = 0; i < truck_weights.length; i++) {
            if (truck_weights[i] > weight) {
                continue;
            }
            while (!bridge.addTruck(truck_weights[i])) {
                bridge.next();
                answer++;
            }
            answer++;
        }
        while (!bridge.isAddable(weight)) {
            bridge.next();
            answer++;
        }
        return answer + 1;
    }
}

class Bridge {
    private final Deque<Integer> queue;
    private final int maxWeight;
    private int curWeight = 0;

    public Bridge(int length, int weight) {
        queue = new ArrayDeque<>(length);
        this.maxWeight = weight;
        for (int i = 0; i < length; i++) {
            queue.addFirst(0);
        }
    }

    public boolean isAddable(int truck) {
        return truck + curWeight - queue.peekLast() <= maxWeight;
    }

    public boolean addTruck(int truck) {
        if (!isAddable(truck)) {
            return false;
        }
        curWeight = curWeight + truck - queue.pollLast();
        queue.addFirst(truck);
        return true;
    }

    public void next() {
        curWeight = curWeight - queue.pollLast();
        queue.addFirst(0);
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
