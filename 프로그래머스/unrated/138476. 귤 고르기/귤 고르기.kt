class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val group = tangerine.groupBy { it }
        val desc = group.map { it.value.size }.sortedDescending()

        var remain = k
        var res = 0

        for (value in desc) {
            if (remain < 1) break

            remain -= value
            res++
        }

        return res
    }
}