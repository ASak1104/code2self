import java.io.StreamTokenizer
import java.util.PriorityQueue

const val INF = 1e9.toInt()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val edges = Array(n + 1) { arrayListOf<Edge>() }

    repeat(m) {
        edges[readInt()].add(Edge(readInt(), readInt()))
    }

    val s = readInt()
    val e = readInt()
    val dists = IntArray(n + 1) { INF }
    val pq = PriorityQueue<Int>(compareBy { dists[it] })

    dists[s] = 0
    pq.add(s)

    while (pq.isNotEmpty()) {
        val u = pq.poll()

        if (dists[u] >= dists[e]) break

        for ((v, w) in edges[u]) {
            val dw = dists[u] + w

            if (dw < dists[v]) {
                dists[v] = dw

                if (pq.peek() != v) pq.add(v)
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("${dists[e]}")
        flush()
        close()
    }
}

data class Edge(val v: Int, val w: Int)