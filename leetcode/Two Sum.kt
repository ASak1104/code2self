package leatcode

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

fun main() {
    val sol = Solution()
    val answer = sol.twoSum(
        intArrayOf(2, 7, 11, 15),
        9
    )
    println(answer.contentToString())
}