import java.io.StreamTokenizer
import java.util.*

val INF = 1e8.toInt()
val st = StreamTokenizer(System.`in`.bufferedReader())

lateinit var edges: Array<ArrayList<Node>>
var n = 0

fun readInt(): Int = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    n = readInt()
    val m = readInt()

    edges = Array(n) { ArrayList<Node>() }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        edges[u] += Node(v, w)
        edges[v] += Node(u, w)
    }

    val v1 = readInt() - 1
    val v2 = readInt() - 1

    val res = minOf(
        dijkstra(0, v1) + dijkstra(v1, v2) + dijkstra(v2, n - 1),
        dijkstra(0, v2) + dijkstra(v2, v1) + dijkstra(v1, n - 1)
    )

    with(System.out.bufferedWriter()) {
        append("${if (res < INF) res else -1}")
        flush()
        close()
    }
}

fun dijkstra(s: Int, e: Int): Int {
    if (s == e) return 0

    val pq = PriorityQueue(compareBy(Node::weight))
    val dists = IntArray(n) { INF }

    pq += Node(s, 0)
    dists[s] = 0

    while (pq.isNotEmpty()) {
        val u = pq.poll()

        for (v in edges[u.node]) {
            val w = u.weight + v.weight

            if (dists[v.node] > w) {
                dists[v.node] = w
                pq += Node(v.node, w)
            }
        }
    }

    return dists[e]
}

data class Node(val node: Int, val weight: Int)