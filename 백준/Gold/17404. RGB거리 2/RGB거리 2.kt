import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val houses = Array(n) { readLine().split(' ').map(String::toInt) }
    val dp = Array(3) { Array(n) { IntArray(3) } }
    dp.forEachIndexed { i, arrays -> arrays[0].fill(houses[0][i]) }
    for (i in 0..2) {
        dp[i][1][i] = 2001
        dp[i][1][(i + 2) % 3] = dp[i][0][i] + houses[1][(i + 2) % 3]
        dp[i][1][(i + 1) % 3] = dp[i][0][i] + houses[1][(i + 1) % 3]
    }
    for (r in 2..n - 2) {
        for (c in 0..2) {
            dp[0][r][c] = min(dp[0][r - 1][(c + 2) % 3], dp[0][r - 1][(c + 1) % 3]) + houses[r][c]
            dp[1][r][c] = min(dp[1][r - 1][(c + 2) % 3], dp[1][r - 1][(c + 1) % 3]) + houses[r][c]
            dp[2][r][c] = min(dp[2][r - 1][(c + 2) % 3], dp[2][r - 1][(c + 1) % 3]) + houses[r][c]
        }
    }
    var min = 1_000_001
    for (start in 0..2) {
        for (end in 0..2) {
            if (start == end) continue
            val localMin = min(dp[start][n - 2][(end + 2) % 3], dp[start][n - 2][(end + 1) % 3]) + houses[n - 1][end]
            if (min > localMin) min = localMin
        }
    }
    println(min)
    close()
}