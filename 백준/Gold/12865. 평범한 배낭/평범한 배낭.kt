import java.io.StreamTokenizer
import kotlin.math.max

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val k = readInt()
    val items = MutableList(n + 1) {
        when (it) {
            0 -> Item(0, 0)
            else -> Item(readInt(), readInt())
        }
    }
    val dp = Array(n + 1) { IntArray(k + 1) }

    for (i in 1..n) {
        val (w, v) = items[i]

        for (cap in 1..k) {
            if (cap < w) {
                dp[i][cap] = dp[i - 1][cap]
            } else {
                dp[i][cap] = max(dp[i - 1][cap], dp[i - 1][cap - w] + v)
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("${dp[n][k]}")
        flush()
        close()
    }
}

data class Item(val w: Int, val v: Int)