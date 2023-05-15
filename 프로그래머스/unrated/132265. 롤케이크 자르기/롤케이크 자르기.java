import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Map<Integer, Integer> leftMap = new HashMap<>();
        leftMap.put(topping[0], 1);
        Map<Integer, Integer> rightMap = new HashMap<>();
        for (int i = 1; i < topping.length; i++) {
            int rightValue = rightMap.getOrDefault(topping[i], 0);
            rightMap.put(topping[i], rightValue + 1);
        }
        int i = 1;
        while (leftMap.keySet().size() <= rightMap.keySet().size()) {
            if (leftMap.keySet().size() == rightMap.keySet().size()) {
                answer++;
            }
            int key = topping[i++];
            int leftValue = leftMap.getOrDefault(key, 0);
            leftMap.put(key, leftValue + 1);

            int rightValue = rightMap.get(key);
            if (rightValue == 1) {
                rightMap.remove(key);
            } else {
                rightMap.put(key, rightValue - 1);
            }
        }
        return answer;
    }
}