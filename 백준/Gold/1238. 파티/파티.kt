import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, x) = StringTokenizer(readLine()).run {
        Triple(nextToken().toInt(), nextToken().toInt(), nextToken().toInt() - 1)
    }
    val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }

    repeat(m) {
        with(StringTokenizer(readLine())) {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1
            val c = nextToken().toInt()

            dist[u][v] = c
        }

        if (it < n) dist[it][it] = 0
    }

    for (k in dist.indices) {
        for (u in dist.indices) {
            for (v in dist.indices) {
                if (dist[u][k] == Int.MAX_VALUE || dist[k][v] == Int.MAX_VALUE) continue

                val d = dist[u][k] + dist[k][v]

                if (dist[u][v] > d) dist[u][v] = d
            }
        }
    }

    var res = 0

    for (u in dist.indices) {
        val t = dist[u][x] + dist[x][u]

        if (res < t) res = t
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
    close()
}