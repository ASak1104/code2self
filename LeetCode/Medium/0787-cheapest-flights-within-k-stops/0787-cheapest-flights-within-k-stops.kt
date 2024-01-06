import java.util.*

const val MAX = 1e9.toInt()

class Solution {

    lateinit var edges: Array<ArrayList<Pair<Int, Int>>>

    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        edges = Array(n) { arrayListOf() }

        for ((u, v, w) in flights) {
            if (u == dst) {
                continue
            }

            edges[u] += v to w
        }

        return dijkstra(src, dst, k)
    }

    fun dijkstra(src: Int, dst: Int, k: Int): Int {
        val dists = Array(edges.size) { Array(k + 1) { MAX } }
        val pq = PriorityQueue(compareBy(Node::cost))

        pq += Node(src, 0, 0)
        dists[src][0] = 0

        while (pq.isNotEmpty()) {
            val (u, count, cost) = pq.poll()

            for ((v, w) in edges[u]) {
                if (cost + w >= dists[v][count]) {
                    continue
                }

                dists[v][count] = cost + w

                if (count < k) {
                    pq += Node(v, count + 1, cost + w)
                }
            }
        }

        val min = dists[dst].min()

        return if (min != MAX)
            min
        else
            -1
    }

    data class Node(val node: Int, val count: Int, val cost: Int)
}
