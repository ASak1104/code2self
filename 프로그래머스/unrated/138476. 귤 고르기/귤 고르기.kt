class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val group = tangerine.groupBy { it }
        val desc = ArrayList<Int>(group.size)

        group.forEach { desc += it.value.size }
        
        desc.sortDescending()

        var remain = k
        var res = 0

        for (v in desc) {
            if (remain < 1) break
            
            remain -= v
            res++
        }

        return res
    }
}