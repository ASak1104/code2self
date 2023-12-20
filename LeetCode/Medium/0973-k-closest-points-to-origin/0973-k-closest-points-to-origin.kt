import java.util.*
import kotlin.math.sqrt

class Solution {

    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val pq = PriorityQueue<IntArray>(k, compareByDescending { distance(it) })

        for (point in points) {
            if (pq.size < k) {
                pq.offer(point)
                continue
            }

            if (distance(point) < distance(pq.peek())) {
                pq.poll()
                pq.offer(point)
            }
        }

        return pq.toTypedArray()
    }

    fun distance(point: IntArray): Double {
        return sqrt(point[0].toDouble() * point[0] + point[1] * point[1])
    }
}
