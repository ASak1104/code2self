import java.io.StreamTokenizer
import java.util.*

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int = with(st) {
    nextToken()
    return nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()

    val edges = Array(n + 1) { ArrayList<Int>() }
    val indegrees = IntArray(n + 1)

    repeat(m) {
        val u = readInt()
        val v = readInt()

        edges[u] += v
        indegrees[v]++
    }

	val pq = PriorityQueue<Int>(n)

    for (u in 1..n) {
        if (indegrees[u] == 0) {
            pq += u
        }
    }

    val bw = System.out.bufferedWriter()

    while (pq.isNotEmpty()) {
        val u = pq.poll()

        bw.append("$u ")

        for (v in edges[u]) {
            indegrees[v]--

            if (indegrees[v] == 0) {
                pq += v
            }
        }
    }

    bw.flush()
    bw.close()
}