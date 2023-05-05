## 문제

- 출처: [프로그래머스](https://school.programmers.co.kr/)
- 문제: [베스트앨범](https://school.programmers.co.kr/learn/courses/30/lessons/42579)
- 등급: Lv. 3
- 분류: 해시

<br>

## 코드 - Java

```java
import java.util.*;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, int[]> genreMap = new HashMap<>();
        Map<String, Integer> counter = new HashMap<>();
        for (int i = 0; i < plays.length; i++) {
            if (!genreMap.containsKey(genres[i])) {
                genreMap.put(genres[i], new int[]{i, -1});
                counter.put(genres[i], plays[i]);
                continue;
            }
            counter.put(genres[i], counter.get(genres[i]) + plays[i]);
            int[] arr = genreMap.get(genres[i]);
            if (plays[i] > plays[arr[0]]) {
                arr[1] = arr[0];
                arr[0] = i;
            } else if (arr[1] == -1 || plays[i] > plays[arr[1]]) {
                arr[1] = i;
            }
        }

        List<Integer> answer = new ArrayList<>();
        List<Map.Entry<String, Integer>> topGenres = new ArrayList<>(counter.entrySet());
        topGenres.sort((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<String, Integer> topGenre : topGenres) {
            String key = topGenre.getKey();
            for (int g : genreMap.get(key)) {
                if (g > -1) {
                    answer.add(g);
                }
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
```

[//]: # (<br>)

[//]: # (### 해설)
