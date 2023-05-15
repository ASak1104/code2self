import java.util.*;

class Solution {
    private String[] words;
    private String target;
    private int answer;

    public int solution(String begin, String target, String[] words) {
        if (!Set.of(words).contains(target))
            return 0;
        this.words = words;
        this.target = target;
        answer = words.length + 1;
        travel(begin, 0);
        return answer % (words.length + 1);
    }

    private void travel(String current, long visitedBit) {
        if (current.equals(target)) {
            answer = Math.min(answer, Long.bitCount(visitedBit));
            return;
        }
        if (Long.bitCount(visitedBit) == words.length)
            return;
        for (int i = 0; i < words.length; i++) {
            if ((1L << i & visitedBit) != 0)
                continue;
            if (isConvertable(current, words[i]))
                travel(words[i], visitedBit | 1L << i);
        }
    }

    private boolean isConvertable(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                diff += 1;
            if (diff > 1)
                return false;
        }
        return true;
    }
}