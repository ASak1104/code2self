class Solution {

    boolean[] contains;
    int[] counts;
    int start, end;

    public String minWindow(String s, String t) {
        contains = new boolean[123];
        counts = new int[123];

        for (int i = 0; i < t.length(); i++) {
            contains[t.charAt(i)] = true;
            counts[t.charAt(i)]++;
        }

        int left = 0;
        int count = t.length();

        start = 0;
        end = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (!contains[rightChar]) {
                continue;
            }

            if (--counts[rightChar] >= 0) {
                count--;
            }

            while (count == 0) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }

                char leftChar = s.charAt(left);

                if (contains[leftChar]) {
                    if (++counts[leftChar] > 0) {
                        count++;
                    }
                }

                left++;
            }
        }

        if (end >= s.length()) {
            return "";
        }

        return s.substring(start, end + 1);
    }

}
