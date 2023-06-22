import java.io.StreamTokenizer
import kotlin.math.max

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val n = readInt()
    val dp = Array(n + 1) { IntArray(it + 2) }

    repeat(n + 1) { r ->
        repeat(r) { c -> dp[r][c + 1] = readInt() }
    }

    for (r in 2..n) {
        for (c in 1..r) {
            dp[r][c] += max(dp[r - 1][c - 1], dp[r - 1][c])
        }
    }

    with(System.out.bufferedWriter()) {
        append("${dp[n].max()}")
        flush()
        close()
    }
}