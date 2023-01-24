package leetcode

import kotlin.math.max

class Solution {
    fun trap(height: IntArray): Int {
        var area = 0
        var left = 0
        var right = height.size - 1
        var leftMax = height[left]
        var rightMax = height[right]

        while (left < right) {
            if (height[left] < height[right]) {
                area += leftMax - height[left++]
                leftMax = max(leftMax, height[left])
            } else {
                area += rightMax - height[right--]
                rightMax = max(rightMax, height[right])
            }
        }
        return area
    }
}

fun main() {
    val sol = Solution()
    val answer = sol.trap(
        intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1),
    )
    println(answer)
}