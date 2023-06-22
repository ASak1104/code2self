import java.io.StreamTokenizer
import kotlin.math.min

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val n = readInt()
    val dp = Array(n) { IntArray(3) { readInt() } }

    for (i in 1 until n) {
        for (j in 0..2) {
            dp[i][j] += min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3])
        }
    }
    
    with(System.out.bufferedWriter()) {
        append("${dp[n - 1].min()}")
        flush()
        close()
    }
}