fun main() {
    val n = readln().toInt()
    val dp = Array(10) { Array(n + 1) { IntArray(1024) } }

    for (i in 0..9) {
        dp[i][1][1 shl i] = 1
    }
    for (k in 2..n) {
        for (i in 0..9) {
            for (b in 0 until 1024) {
                val ib = b or (1 shl i)

                if (i > 0) dp[i][k][ib] = (dp[i][k][ib] + dp[i - 1][k - 1][b]) % 1_000_000_000

                if (i < 9) dp[i][k][ib] = (dp[i][k][ib] + dp[i + 1][k - 1][b]) % 1_000_000_000
            }
        }
    }
    var res = 0

    for (i in 1..9) {
        res = (res + dp[i][n][1023]) % 1_000_000_000
    }
    print(res)
}