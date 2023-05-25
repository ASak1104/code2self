import kotlin.math.abs

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val nums = readLine().split(' ').map(String::toLong).sorted()
    val res = LongArray(4) { Long.MAX_VALUE }

    outer@ for (i in 0..n - 3) {
        var left = i + 1
        var right = nums.lastIndex

        while (left < right) {
            val sum = nums[i] + nums[left] + nums[right]

            if (abs(sum) < res[0]) {
                res[0] = abs(sum)
                res[1] = nums[i]
                res[2] = nums[left]
                res[3] = nums[right]
            }
            when {
                sum < 0 -> left++
                sum > 0 -> right--
                else -> break@outer
            }
        }
    }
    print(res.slice(1..3).joinToString(" "))
}