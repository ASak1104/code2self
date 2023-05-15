import java.util.*;

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