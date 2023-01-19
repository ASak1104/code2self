import kotlin.math.pow

class Solution {
    fun solution(numbers: IntArray): String {
        val cmp = Comparator<Int> { a, b ->
            when {
                a == b -> a - b
                else -> a - b
            }
        }
        val sortedNumber = numbers.sortedWith(cmp)
        return sortedNumber.joinToString("")
    }

    private fun indexOf(number: Int, idx: Int): Int {
        if (number < 10F.pow(idx)) {
            return indexOf(number, idx - 1)
        }
        var res = number
        for (i in 0 until idx) {

        }
        return res
    }

    private fun length(number: Int): Int {
        return number.toString().length
    }
}

fun main() {
    val s = Solution()
    println(s.solution(intArrayOf(6, 10, 2)))
}

