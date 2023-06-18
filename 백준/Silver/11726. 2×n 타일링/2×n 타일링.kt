fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    close()
    
    val dp = IntArray(n + 1) { 1 }

    for (k in 2..n) {
        dp[k] = (dp[k - 1] + dp[k - 2]) % 10_007
    }

    with(System.out.bufferedWriter()) {
        append("${dp[n]}").flush()
        close()
    }
}