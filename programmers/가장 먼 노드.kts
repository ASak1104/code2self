import kotlin.Int.Companion.MAX_VALUE

class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {

        val edges = Array<MutableSet<Int>>(n) { mutableSetOf() }
        for ((u, v) in edge) {
            edges[u - 1].add(v - 1)
            edges[v - 1].add(u - 1)
        }

        val dist = dijkstra(0, edges)
        val distMax = dist.maxOrNull()
        return dist.count { d -> d == distMax }
    }

    fun dijkstra(u: Int, edges: Array<MutableSet<Int>>): IntArray {
        val dist = IntArray(edges.size) { MAX_VALUE }
        dist[u] = 0

        val queue = ArrayDeque<Int>()
        queue.add(u)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            for (v in edges[node]) {
                if (dist[node] + 1 < dist[v]) {
                    dist[v] = dist[node] + 1
                    queue.add(v)
                }
            }
        }

        return dist
    }
}


fun main() {
    val s = Solution()
    println(s.solution(
        6,
        arrayOf(
            intArrayOf(3, 6), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(1, 3), intArrayOf(1, 2), intArrayOf(2, 4), intArrayOf(5, 2)
        )
    ))
}