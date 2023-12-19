import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    static Deque<Character> deque;
    static int[] lastIndices;
    static boolean[] visits;
    static char[] chars;

    public String removeDuplicateLetters(String s) {
        init(s);

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int index = c - 'a';

            if (visits[index]) {
                continue;
            }

            while (isPollable(i, c)) {
                int pollIndex = deque.pollLast() - 'a';

                visits[pollIndex] = false;
            }

            visits[index] = true;
            deque.addLast(c);
        }

        StringBuilder sb = new StringBuilder(26);

        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }

        return sb.toString();
    }

    private void init(String s) {
        deque = new ArrayDeque<>(26);
        lastIndices = new int[26];
        visits = new boolean[26];
        chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            lastIndices[chars[i] - 'a'] = i;
        }
    }

    private boolean isPollable(int i, int c) {
        return !deque.isEmpty() && deque.peekLast() > c && i < lastIndices[deque.peekLast() - 'a'];
    }

}
