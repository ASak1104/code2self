class Solution {
    fun solution(numbers: IntArray): String {
        val strs = numbers.map { it.toString() }
        val sorted_strs = strs.sortedWith { a, b -> (b + a).toInt() - (a + b).toInt() }
        return sorted_strs.joinToString("").let {
            if (it.startsWith("0")) "0" else it
        }
    }
}