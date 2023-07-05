import java.io.StreamTokenizer
import java.util.*

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val edges = Array(n) { ArrayList<Edge>() }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        edges[u] += Edge(v, w)
        edges[v] += Edge(u, w)
    }

    val pq = PriorityQueue(compareBy(Edge::w))
    val visit = BooleanArray(n)
    val res = ArrayList<Int>()

    pq += Edge(0, 0)

    while (pq.isNotEmpty()) {
        val (u, d) = pq.poll()

        if (visit[u]) continue

        visit[u] = true
        res += d

        for ((v, w) in edges[u]) {
            if (visit[v]) continue

            pq.add(Edge(v, w))
        }
    }

    with(System.out.bufferedWriter()) {
        append("${res.sum() - res.max()}")
        flush()
        close()
    }
}

data class Edge(val v: Int, val w: Int)