import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    static final int OFFSET = (int) 1e4;

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] counts = new int[OFFSET << 1 + 1];
        int[] maxes = new int[nums.length - k + 1];
        int mIdx = 0;

        for (int i = 0; i < k; i++) {
            counts[nums[i] + OFFSET]++;
            pq.add(nums[i]);
        }

        maxes[mIdx++] = pq.peek();

        for (int left = 1; left < nums.length - k + 1; left++) {
            int right = left + k - 1;

            counts[nums[left - 1] + OFFSET]--;
            counts[nums[right] + OFFSET]++;
            pq.add(nums[right]);

            while (counts[pq.peek() + OFFSET] <= 0) {
                pq.poll();
            }

            maxes[mIdx++] = pq.peek();
        }

        return maxes;
    }

}
