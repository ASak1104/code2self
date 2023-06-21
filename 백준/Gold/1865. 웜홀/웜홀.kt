import java.util.*

const val INF = 10001

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val (n, m, w) = StringTokenizer(readLine()).run {
            Triple(nextToken().toInt(), nextToken().toInt(), nextToken().toInt())
        }
        val edges = Array(n) { IntArray(n) { INF } }

        repeat(m) {
            with(StringTokenizer(readLine())) {
                val u = nextToken().toInt() - 1
                val v = nextToken().toInt() - 1
                val c = nextToken().toInt()

                if (edges[u][v] > c) {
                    edges[u][v] = c
                    edges[v][u] = c
                }
            }
        }

        repeat(w) {
            with(StringTokenizer(readLine())) {
                val u = nextToken().toInt() - 1
                val v = nextToken().toInt() - 1
                val c = -nextToken().toInt()

                if (edges[u][v] > c) {
                    edges[u][v] = c
                }
            }
        }

        val dists = IntArray(n)

        repeat(n - 1) {
            for (u in edges.indices) {
                for (v in edges.indices) {
                    if (edges[u][v] == INF) continue

                    val d =  dists[u] + edges[u][v]

                    if (dists[v] > d) {
                        dists[v] = d
                    }
                }
            }
        }

        for (u in edges.indices) {
            for (v in edges.indices) {
                if (edges[u][v] == INF) continue

                val d =  dists[u] + edges[u][v]

                if (dists[v] > d) {
                    bw.append("YES\n")
                    return@repeat
                }
            }
        }

        bw.append("NO\n")
    }
    close()

    bw.flush()
    bw.close()
}