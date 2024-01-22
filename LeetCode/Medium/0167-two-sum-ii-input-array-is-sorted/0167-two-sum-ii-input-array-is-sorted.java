class Solution {

    public static final int NOT_FOUND = -1;

    public int[] twoSum(int[] numbers, int target) {
        int prev = numbers[0] - 1;

        for (int left = 0; left < numbers.length - 1; left++) {
            if (numbers[left] == prev) {
                continue;
            }

            int right = bisect(numbers, left + 1, target - numbers[left]);

            if (right != NOT_FOUND) {
                return new int[]{left + 1, right + 1};
            }

            prev = numbers[left];
        }

        return new int[0];
    }

    int bisect(int[] nums, int left, int target) {
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int value = nums[mid];

            if (value == target) {
                return mid;
            }

            if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return NOT_FOUND;
    }

}
