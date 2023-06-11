import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val matrices = Array(n) {
        StringTokenizer(readLine()).run { Matrix(nextToken().toInt(), nextToken().toInt()) }
    }
    close()

    val dp = Array(n) { IntArray(n) }

    for (size in 1 until n) {
        for (s in 0 until n - size) {
            val e = s + size
            dp[s][e] = Int.MAX_VALUE

            for (k in s until s + size) {
                val v = dp[s][k] + dp[k + 1][e] + matrices[s].row * matrices[k].col * matrices[e].col
                if (dp[s][e] > v) dp[s][e] = v
            }
        }
    }
    print(dp[0][n - 1])
}

data class Matrix(val row: Int, val col: Int)