class Solution {

    int[][] prefixCounts;
    int[] counts;
    int maxSize;

    public int characterReplacement(String s, int k) {
        prefixCounts = new int[s.length() + 1][91];
        counts = new int[91];

        for (int i = 1; i <= s.length(); i++) {
            counts[s.charAt(i - 1)]++;

            for (char c = 'A'; c <= 'Z'; c++) {
                prefixCounts[i][c] = counts[c];
            }
        }

        for (int right = s.length(); right > 0; right--) {
            maxSize = Math.max(maxSize, right - findLeft(right, k) + 1);
        }

        return maxSize;
    }

    private int findLeft(int end, int k) {
        int ret = end;
        int left = 1;
        int right = end;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int size = end - mid + 1;
            int maxCount = 0;

            if (size < maxSize) {
                right = mid - 1;
                continue;
            }

            for (char c = 'A'; c <= 'Z'; c++) {
                maxCount = Math.max(maxCount, prefixCounts[end][c] - prefixCounts[mid - 1][c]);
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

