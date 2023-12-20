import java.util.*
import kotlin.math.pow
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
        val x = point[0].toDouble()
        val y = point[1].toDouble()

        return sqrt(x.pow(2) + y.pow(2))
    }
}
