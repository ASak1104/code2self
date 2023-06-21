import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val (n, m, w) = StringTokenizer(readLine()).run {
            Triple(nextToken().toInt(), nextToken().toInt(), nextToken().toInt())
        }
        val edges = Array(n) { mutableMapOf<Int, Int>() }

        repeat(m) {
            with(StringTokenizer(readLine())) {
                val u = nextToken().toInt() - 1
                val v = nextToken().toInt() - 1
                val c = nextToken().toInt()

                edges[u][v] = min(edges[u][v] ?: 10001, c)
                edges[v][u] = min(edges[v][u] ?: 10001, c)
            }
        }

        repeat(w) {
            with(StringTokenizer(readLine())) {
                val u = nextToken().toInt() - 1
                val v = nextToken().toInt() - 1
                val c = -nextToken().toInt()

                edges[u][v] = min(edges[u][v] ?: 10001, c)
            }
        }

        val dists = IntArray(n)

        repeat(n - 1) {
            for (u in edges.indices) {
                for ((v, c) in edges[u].entries) {
                    val d =  dists[u] + c

                    if (dists[v] > d) {
                        dists[v] = d
                    }
                }
            }
        }

        for (u in edges.indices) {
            for ((v, c) in edges[u].entries) {
                val d =  dists[u] + c

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