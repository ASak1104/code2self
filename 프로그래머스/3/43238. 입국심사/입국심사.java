class Solution {

    public long solution(int n, int[] times) {
        long answer = 0L;
        long left = 0L;
        long right = n * (long) 1e9;

        while (left <= right) {
            long mid = (left + right) >>> 1;
            long count = simulate(n, times, mid);

            if (count < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }

    long simulate(int n, int[] times, long givenTime) {
        long count = 0L;

        for (int time : times) {
            count += givenTime / time;
        }

        return count;
    }

}
