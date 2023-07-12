class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val counter = mutableMapOf<Int, Int>()

        for (e in tangerine) {
            counter[e] = (counter[e] ?: 0) + 1
        }

        val desc = counter.values.toMutableList()

        desc.sortDescending()

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