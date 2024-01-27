import java.util.ArrayList;
import java.util.List;

class Solution {

    List<Character> chars;
    int[][] prefixCounts;
    int[] counts;
    int maxSize;

    public int characterReplacement(String s, int k) {
        chars = new ArrayList<>(26);
        prefixCounts = new int[91][s.length() + 1];
        counts = new int[91];

        for (int i = 1; i <= s.length(); i++) {
            counts[s.charAt(i - 1)]++;

            for (char c = 'A'; c <= 'Z'; c++) {
                prefixCounts[c][i] = counts[c];
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (counts[c] != 0) {
                chars.add(c);
            }
        }

        for (int right = s.length(); right > 0; right--) {
            maxSize = Math.max(maxSize, right - findLeft(right, k) + 1);
        }

        return maxSize;
    }

    private int findLeft(int end, int k) {
        int ret = 0;
        int left = 1;
        int right = end;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int size = end - mid + 1;
            int maxCount = 0;

            for (char c : chars) {
                maxCount = Math.max(maxCount, prefixCounts[c][end] - prefixCounts[c][mid - 1]);
            }

            if (size <= maxCount + k) {
                ret = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ret;
    }

}

