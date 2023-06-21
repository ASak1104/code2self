import java.util.*

lateinit var edges: Array<MutableMap<Int, Int>>

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val (n, m, w) = StringTokenizer(readLine()).run {
            Triple(nextToken().toInt(), nextToken().toInt(), nextToken().toInt())
        }
        edges = Array(n) { mutableMapOf() }

        repeat(m) { readEdge(readLine()) }

        repeat(w) { readDirectedEdge(readLine()) }

        val dist = IntArray(n)

        repeat(n - 1) {
            for (u in dist.indices) {
                for ((v, c) in edges[u].entries) {
                    val d = dist[u] + c

                    if (dist[v] > d) {
                        dist[v] = d
                    }
                }
            }
        }

        for (u in dist.indices) {
            for ((v, c) in edges[u].entries) {
                if (dist[v] > dist[u] + c) {
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



fun readEdge(line: String) {
    with(StringTokenizer(line)) {
        val u = nextToken().toInt() - 1
        val v = nextToken().toInt() - 1
        val c = nextToken().toInt()

        if ((edges[u][v] ?: 10001) > c) {
            edges[u][v] = c
            edges[v][u] = c
        }
    }
}

fun readDirectedEdge(line: String) {
    with(StringTokenizer(line)) {
        val u = nextToken().toInt() - 1
        val v = nextToken().toInt() - 1
        val c = -nextToken().toInt()

        if ((edges[u][v] ?: 10001) > c) {
            edges[u][v] = c
        }
    }
}