import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val costs = Array(n) { IntArray(n) }

    repeat(n) { r ->
        val line = StringTokenizer(readLine())
        for (c in 0 until n) {
            costs[r][c] = line.nextToken().toInt()
        }
    }

    val tsp = TSP(n, costs)
    tsp.tsp()
    print(tsp.getMin())
}

class TSP(val n: Int, val costs: Array<IntArray>) {
    val memo = Array(1 shl n) { IntArray(n) { Int.MAX_VALUE } }

    fun tsp() {
        travel(1, 0, 0)
        val last = memo.lastIndex

        for (i in 0 until n) {
            if (costs[i][0] == 0) {
                memo[last][i] = Int.MAX_VALUE
            }
            else if (memo[last][i] != Int.MAX_VALUE) {
                memo[last][i] += costs[i][0]
            }
        }
    }

    fun travel(mask: Int, u: Int, cost: Int) {
        if (mask == memo.lastIndex) {
            if (cost < memo[mask][u]) {
                memo[mask][u] = cost
            }
            return
        }
        for (v in 0 until n) {
            val bit = 1 shl v

            if (mask and bit == bit || costs[u][v] == 0) {
                continue
            }
            val nextMask = mask or bit
            val nextCost = cost + costs[u][v]

            if (nextCost < memo[nextMask][v]) {
                memo[nextMask][v] = nextCost
                travel(nextMask, v, nextCost)
            }
        }
    }

    fun getMin() = memo[memo.lastIndex].min()
}