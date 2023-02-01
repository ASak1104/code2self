package leetcode

import kotlin.math.min

class TrappingRainWater2 {
    // stack
    fun trap(height: IntArray): Int {
        var area = 0
        val stack = ArrayDeque<Int>()

        for ((i, h) in height.withIndex()) {
            while (stack.isNotEmpty() && h > height[stack.last()]) {
                val top = stack.removeLast()
                if (stack.isEmpty())
                    break
                val width = i - stack.last() - 1
                val depth = min(h, height[stack.last()]) - height[top]
                area += width * depth
            }
            stack.addLast(i)
        }
        return area
    }
}

fun main() {
    val sol = TrappingRainWater2()
    val answer = sol.trap(
        intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1),
    )
    println(answer)
}