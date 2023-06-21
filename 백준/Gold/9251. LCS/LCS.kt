import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()
    val k = readLine()

    val dp = Array(n.length + 1) { IntArray(k.length + 1) }

    for (i in 1..n.length) {
        for (j in 1..k.length) {
            dp[i][j] = if (n[i - 1] == k[j - 1])
                dp[i - 1][j - 1] + 1
            else
                max(dp[i - 1][j], dp[i][j - 1])
        }
    }

    with(System.out.bufferedWriter()) {
        append("${dp[n.length][k.length]}")
        flush()
        close()
    }
}