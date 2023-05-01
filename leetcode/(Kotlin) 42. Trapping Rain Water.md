## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
- 등급: Hard

<br>

## 코드 - Kotlin

```kt
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
```

```kt
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
```

```kt
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
```


[//]: # (<br>)

[//]: # (### 해설)
