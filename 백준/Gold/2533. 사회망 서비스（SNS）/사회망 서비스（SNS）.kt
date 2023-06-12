import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val edges = Array(n + 1) { ArrayDeque<Int>() }
    repeat(n - 1) {
        StringTokenizer(readLine()).run {
            val u = nextToken().toInt()
            val v = nextToken().toInt()
            edges[u].addLast(v)
            edges[v].addLast(u)
        }
    }
    close()
    
    val dp = Array(2) { IntArray(n + 1) }
    val visit = BooleanArray(n + 1)

    fun travel(u: Int) {
        visit[u] = true
        dp[1][u] = 1

        for (v in edges[u]) {
            if (visit[v]) continue

            travel(v)
            dp[0][u] += dp[1][v]
            dp[1][u] += min(dp[0][v], dp[1][v])
        }
    }
    travel(1)
    print(min(dp[0][1], dp[1][1]))
}