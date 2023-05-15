import java.util.Arrays;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        Arrays.fill(answer, 1);

        int offset = (int) (left % n);
        int minimum = (int) (left / n + 1);
        for (int i = 0; i < answer.length; i++) {
            answer[i] = Math.max(answer[i] + offset, minimum);
            offset = (offset + 1) % n;
            if (offset == 0) {
                minimum += 1;
            }
        }
        return answer;
    }
}