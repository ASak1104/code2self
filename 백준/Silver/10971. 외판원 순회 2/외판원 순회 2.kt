import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val costs = Array(n) {
        StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } }
    }
    val tsp = TSP(n, costs)
    print(tsp.travel(1, 0))
    close()
}

class TSP(val n: Int, val costs: Array<IntArray>) {
    val MAX_VALUE = 16_000_001
    val memo = Array(1 shl n) { IntArray(n) }

    fun travel(mask: Int, u: Int): Int {
        if (mask == memo.lastIndex) {
            return if (costs[u][0] == 0)
                MAX_VALUE
            else
                costs[u][0]
        }
        if (memo[mask][u] > 0) {
            return memo[mask][u]
        }
        var minCost = MAX_VALUE

        for (v in 0 until n) {
            val bit = 1 shl v

            if (mask and bit == bit || costs[u][v] == 0) {
                continue
            }
            val nextCost = costs[u][v] + travel(mask or bit, v)

            if (nextCost < minCost) {
                minCost = nextCost
            }
        }
        memo[mask][u] = minCost
        return minCost
    }
}