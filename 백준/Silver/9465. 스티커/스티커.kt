import java.io.StreamTokenizer
import kotlin.math.max

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val bw = System.out.bufferedWriter()
    val tc = readInt()

    repeat(tc) {
        val n = readInt()
        val dp = Array(2) { IntArray(n + 2) }

        repeat(2) { r ->
            repeat(n) { dp[r][it + 2] = readInt() }
        }

        for (j in 2 until n + 2) {
            dp[0][j] += max(dp[1][j - 2], dp[1][j - 1])
            dp[1][j] += max(dp[0][j - 2], dp[0][j - 1])
        }

        bw.append("${dp.maxOf { it[n + 1] }}\n")
    }
    bw.flush()
    bw.close()
}