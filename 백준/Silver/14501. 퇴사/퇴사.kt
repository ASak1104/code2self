import java.util.*

val dp = IntArray(21)
val costs = IntArray(16)
val weights = IntArray(16)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    for (day in 1..n) {
        StringTokenizer(readLine()).run {
            costs[day] = nextToken().toInt()
            weights[day] = nextToken().toInt()
        }
    }

    for (day in 1..n) {
        val target = day + costs[day]

        dp[target] = maxOf(dp[target], dp[day] + weights[day])

        for (next in target + 1..n + 1) {
            dp[next] = maxOf(dp[next], dp[next - 1])
        }
    }

    println(dp[n + 1])
    close()
}
