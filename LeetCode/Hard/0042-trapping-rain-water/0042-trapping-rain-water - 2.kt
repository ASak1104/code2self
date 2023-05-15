class TrappingRainWater {
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