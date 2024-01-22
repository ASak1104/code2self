import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    int[] targets, nums;

    public int[] intersection(int[] nums1, int[] nums2) {
        targets = nums1;
        nums = nums2;

        Arrays.sort(nums);

        Set<Integer> intersects = new HashSet<>();

        for (int target : targets) {
            if (intersects.contains(target) || !contains(target)) {
                continue;
            }

            intersects.add(target);
        }

        return intersects.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    boolean contains(int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int value = nums[mid];

            if (value == target) {
                return true;
            }

            if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

}
