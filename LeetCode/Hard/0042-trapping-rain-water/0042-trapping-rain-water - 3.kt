import kotlin.math.max
import kotlin.math.min

class TrappingRainWater3 {
    // two array
    fun trap(height: IntArray): Int {
        var area = 0
        val leftMax = IntArray(height.size) { height.first()}
        val rightMax = IntArray(height.size) { height.last() }
        for (i in 1 until height.size) {
            leftMax[i] = max(leftMax[i - 1], height[i])
        }
        for (i in height.lastIndex - 1 downTo 0) {
            rightMax[i] = max(rightMax[i + 1], height[i])
        }
        for ((i, h) in height.withIndex()) {
            area += min(leftMax[i], rightMax[i]) - h
        }
        return area
    }
}