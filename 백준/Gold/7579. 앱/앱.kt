import java.util.*
import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val mems = StringTokenizer(readLine()).run { Array(n) { nextToken().toInt() } }
    val costs = StringTokenizer(readLine()).run { Array(n) { nextToken().toInt() } }
    close()

    var res = costs.sum()
    val dp = Array(n + 1) { IntArray(res + 1) }
    for (i in 1..n) {
        val c = costs[i - 1]

        for (j in 1..dp[0].lastIndex) {
            dp[i][j] = if (j < c)
                dp[i - 1][j]
            else
                max(dp[i - 1][j - c] + mems[i - 1], dp[i - 1][j])

            if (dp[i][j] >= m && res > j) {
                res = j
            }
        }
    }
    print(res)
}