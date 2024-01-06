import java.util.*

const val MAX = 1e9.toInt()

class Solution {

    lateinit var edges: Array<ArrayList<Edge>>

    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        edges = Array(n) { arrayListOf() }

        for ((u, v, w) in times) {
            edges[u - 1] += Edge(v - 1, w)
        }

        return dijkstra(k - 1)
    }

    fun dijkstra(start: Int): Int {
        val dists: Array<Int> = Array(edges.size) { MAX }
        val pq = PriorityQueue<Edge>(compareBy(Edge::w))

        pq += Edge(start, 0)
        dists[start] = 0

        while (pq.isNotEmpty()) {
            val (u, cost) = pq.poll()

            if (cost > dists[u]) {
                continue
            }

            for ((v, w) in edges[u]) {
                if (dists[v] <= cost + w) {
                    continue
                }

                pq += Edge(v, cost + w)
                dists[v] = cost + w
            }
        }

        if (MAX in dists) {
            return -1
        }

        return dists.max()
    }

    data class Edge(val node: Int, val w: Int)
}
