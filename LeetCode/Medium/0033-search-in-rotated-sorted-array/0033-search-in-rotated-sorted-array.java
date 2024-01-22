class Solution {

    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);
        int leftResult = bisect(nums, 0, pivot, target);
        int rightResult = bisect(nums, pivot + 1, nums.length - 1, target);

        return Math.max(leftResult, rightResult);
    }

    int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] <= nums[right]) {
                return right;
            }

            int mid = (left + right) >>> 1;

            if (nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    int bisect(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) >>> 1;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
