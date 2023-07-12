import java.util.*

class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        val pq = PriorityQueue<Int>(compareByDescending { it })

        tangerine.groupBy { it }.forEach { pq += it.value.size }

        var remain = k
        var res = 0

        while (remain > 0) {
            remain -= pq.poll()
            res++
        }

        return res
    }
}