import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val edges = Array(n) { BooleanArray(n) }

    repeat(readLine().toInt()) {
        StringTokenizer(readLine()).run {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1

            edges[u][v] = true
            edges[v][u] = true
        }
    }

    var res = -1
    val visit = BooleanArray(n)

    fun travel(u: Int) {
        visit[u] = true
        res++

        for (v in edges.indices) {
            if (!visit[v] && edges[u][v]) travel(v)
        }
    }

    travel(0)

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}