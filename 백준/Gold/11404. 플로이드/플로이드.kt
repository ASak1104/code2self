import java.util.*

const val INF = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val dist = Array(n) { IntArray(n) { INF } }

    repeat(n) { dist[it][it] = 0 }

    repeat(m) {
        StringTokenizer(readLine()).run {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1
            val c = nextToken().toInt()

            if (dist[u][v] > c) dist[u][v] = c
        }
    }

    close()

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