import kotlin.math.max

class TrappingRainWater {
    // two pointer
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