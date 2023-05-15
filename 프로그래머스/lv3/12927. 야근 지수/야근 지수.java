import java.util.*;
import java.util.Map.Entry;

class Solution {
    public static long solution(int n, int[] works) {
        long answer = 0;
        int max_work = 1;
        Map<Integer, Integer> workMap = new HashMap<>();
        for (int work : works) {
            max_work = Math.max(max_work, work);
            workMap.put(work, workMap.getOrDefault(work, 0) + 1);
        }
        while (n > 0 && max_work > 0) {
            Integer count = workMap.get(max_work);
            if (n < count) {
                workMap.put(max_work, count - n);
                count = n;
            } else {
                workMap.remove(max_work);
            }
            if (--max_work > 0) {
                workMap.put(max_work, workMap.getOrDefault(max_work, 0) + count);
            }
            n -= count;
        }
        for (Entry<Integer, Integer> entry : workMap.entrySet()) {
            answer += Math.pow(entry.getKey(), 2) * entry.getValue();
        }
        return answer;
    }
}