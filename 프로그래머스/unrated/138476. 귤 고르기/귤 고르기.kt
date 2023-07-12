import java.util.*

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val group = tangerine.groupBy { it }
        val pq = PriorityQueue<Int>(compareByDescending { it })

        group.forEach { pq += it.value.size }

        var remain = k
        var res = 0

        while (remain > 0) {
            remain -= pq.poll()
            res++
        }

        return res
    }
}