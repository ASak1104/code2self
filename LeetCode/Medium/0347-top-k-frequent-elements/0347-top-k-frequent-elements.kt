import java.util.*

class Solution {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val counts: MutableMap<Int, Int> = mutableMapOf()
        val pq: PriorityQueue<Int> = PriorityQueue(k, compareBy { counts[it] })

        for (num in nums) {
            counts[num] = (counts[num] ?: 0) + 1
        }

        for ((num, count) in counts.entries) {
            if (pq.size < k) {
                pq += num
                continue
            }

            if (count > counts[pq.first()]!!) {
                pq.poll()
                pq += num
            }
        }

        return pq.toIntArray()
    }
}

