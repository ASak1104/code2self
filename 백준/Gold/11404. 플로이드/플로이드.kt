import java.io.StreamTokenizer

const val INF = Int.MAX_VALUE

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val m = readInt()
    val dist = Array(n) { IntArray(n) { INF } }

    repeat(n) { dist[it][it] = 0 }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val c = readInt()

        if (dist[u][v] > c) dist[u][v] = c
    }

    for (k in dist.indices) {
        for (u in dist.indices) {
            for (v in dist.indices) {
                if (dist[u][k] == INF || dist[k][v] == INF) continue

                val d = dist[u][k] + dist[k][v]

                if (dist[u][v] > d) dist[u][v] = d
            }
        }
    }

    with(System.out.bufferedWriter()) {
        dist.forEach { row ->
            row.forEach { append(if (it == INF) "0 " else "$it ") }
            newLine()
        }
        flush()
        close()
    }
}