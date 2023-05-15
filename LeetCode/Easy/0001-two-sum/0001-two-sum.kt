class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numsMap = mutableMapOf<Int, Int>()
        nums.forEachIndexed { index, num ->
            numsMap[target - num]?.let { return intArrayOf(index, it) }
            numsMap[num] = index
        }
        return intArrayOf()
    }
}