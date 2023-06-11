fun main() {
    val n = readln().toInt()
    val dp = Array(10) { IntArray(n + 1) }

    for (i in 0..9) {
        dp[i][1] = 1
    }
    for (j in 2..n) {
        for (i in 0..9) {
            if (i > 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % 1_000_000_000
            if (i < 9) dp[i][j] = (dp[i][j] + dp[i + 1][j - 1]) % 1_000_000_000
        }
    }
    var res = 0

    for (i in 1..9) {
        res = (res + dp[i][n]) % 1_000_000_000
    }
    print(res)
}