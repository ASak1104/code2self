import java.io.StreamTokenizer
import kotlin.math.abs

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int {
    st.nextToken()
    return st.nval.toInt()
}

fun main() {
    val ddr = DDR()
    var next = readInt()

    while (next > 0) {
        ddr.move(next)

        next = readInt()
    }

    with(System.out.bufferedWriter()) {
        append("${ddr.cost}")
        flush()
        close()
    }
}

class DDR {
    val INF = 1e9.toInt()
    val dp = Array(100_001) { Array(5) { IntArray(5) { INF } } }
    var count = 0
    val cost
        get() = dp[count].minOf { it.min() }

    init {
        dp[0][0][0] = 0
    }

    fun move(next: Int) {
        count++

        for (i in 0..4) {
            for (j in 0..4) {
                if (i > 0 && i == j) continue

                val dist = dist(j, next)

                dp[count][i][next] = minOf(dp[count][i][next], dp[count - 1][i][j] + dist)
                dp[count][next][i] = minOf(dp[count][next][i], dp[count - 1][j][i] + dist)
            }
        }
    }

    fun dist(u: Int, v: Int): Int {
        return when {
            u == 0 -> 2
            u == v -> 1
            abs(u - v) == 2 -> 4
            else -> 3
        }
    }
}