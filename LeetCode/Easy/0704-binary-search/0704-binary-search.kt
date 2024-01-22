class Solution {

    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = (left + right) ushr 1
            val value = nums[mid]

            if (value == target) {
                return mid
            }

            if (value < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return -1
    }
}
