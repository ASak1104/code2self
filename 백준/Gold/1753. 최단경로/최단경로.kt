import java.io.StreamTokenizer
import java.util.*

const val INF = 1e9.toInt()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val m = readInt()
    val root = readInt() - 1
    val edges = Array(n) { mutableMapOf<Int, Int>() }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        if ((edges[u][v] ?: 11) > w) edges[u][v] = w
    }

    val dist = IntArray(n) { INF }
    val pq = PriorityQueue<Int>(compareBy { dist[it] })

    dist[root] = 0
    pq.add(root)

    while (pq.isNotEmpty()) {
        val u = pq.poll()

        for ((v, w) in edges[u]) {
            val d = dist[u] + w

            if (dist[v] > d) {
                dist[v] = d
                pq.add(v)
            }
        }
    }

    with(System.out.bufferedWriter()) {
        dist.forEach { append("${if (it == INF) "INF" else it}\n") }
        flush()
        close()
    }
}