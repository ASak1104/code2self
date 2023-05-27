fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val testCase = readLine().toInt()
    val dp = Array(41) { 0 to 0 }
    dp[0] = 1 to 0
    dp[1] = 0 to 1

    for (i in 2..dp.lastIndex)
        dp[i] = dp[i - 1].first + dp[i - 2].first to dp[i - 1].second + dp[i - 2].second

    repeat(testCase) {
        val n = readLine().toInt()
        bw.append("${dp[n].first} ${dp[n].second}")
        bw.newLine()
    }

    bw.flush()
    bw.close()
    close()
}