import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array(n) { BooleanArray(n) }

    repeat(m) {
        with(StringTokenizer(readLine())) {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1

            edges[u][v] = true
            edges[v][u] = true
        }
    }
    close()

    var networks = 0
    val visit = BooleanArray(n)

    fun travel(u: Int) {
        visit[u] = true

        for (v in edges[u].indices) {
            if (!visit[v] && edges[u][v]) travel(v)
        }
    }

    for (u in edges.indices) {
        if (!visit[u]) {
            travel(u)
            networks++
        }
    }

    with(System.out.bufferedWriter()) {
        append("$networks").flush()
        close()
    }
}