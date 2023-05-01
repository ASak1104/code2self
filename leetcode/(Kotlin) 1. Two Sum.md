## 문제

- 출처: [Leetcode](https://leetcode.com/problemset/all/)
- 문제: [1. Two Sum](https://leetcode.com/problems/two-sum/)
- 등급: Easy

<br>

## 코드 - Kotlin

```kt
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
```

[//]: # (<br>)

[//]: # (### 해설)
