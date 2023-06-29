import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val edges = Array(n + 1) { ArrayList<Int>() }

    repeat(n - 1) {
        val u = readInt()
        val v = readInt()

        edges[u] += v
        edges[v] += u
    }

    val dp = Array(n + 1) { IntArray(2) }
    val visit = BooleanArray(n + 1)

    fun travel(u: Int) {
        visit[u] = true
        dp[u][1] = 1

        for (v in edges[u]) {
            if (visit[v]) continue

            travel(v)

            dp[u][0] += dp[v][1]
            dp[u][1] += dp[v].min()
        }
    }

    travel(1)

    with(System.out.bufferedWriter()) {
        append("${dp[1].min()}")
        flush()
        close()
    }
}