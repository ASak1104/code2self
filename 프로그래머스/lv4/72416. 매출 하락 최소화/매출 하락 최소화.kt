import java.util.PriorityQueue

class Solution {
    lateinit var depths: IntArray
    lateinit var edges: Array<ArrayList<Int>>
    lateinit var dp: Array<IntArray>
    var n = 0

    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        n = sales.size
        depths = IntArray(n + 1)
        edges = Array(n + 1) { ArrayList<Int>() }
        dp = Array(2) { IntArray(n + 1) }

        for (u in 1..n) {
            dp[1][u] = sales[u - 1]
        }

        for ((u, v) in links) {
            edges[u] += v
        }

        travel(1, 0)

        val pq = PriorityQueue<Int>(n, compareBy { -depths[it] })

        for (u in 1..n) {
            if (edges[u].size > 0) {
                pq.offer(u)
            }
        }

        while (pq.isNotEmpty()) {
            val u = pq.poll()
            var vSum = 0
            var min = Int.MAX_VALUE

            for (v in edges[u]) {
                dp[1][u] += minOf(dp[0][v], dp[1][v])
                vSum += minOf(dp[0][v], dp[1][v])
            }

            for (v in edges[u]) {
                if (dp[0][v] < dp[1][v]) {
                    min = minOf(min, dp[1][v] + vSum - dp[0][v])
                } else {
                    min = minOf(min, vSum)
                }
            }

            dp[0][u] = min
        }

        return minOf(dp[0][1], dp[1][1])
    }

    fun travel(u: Int, p: Int) {
        depths[u] = depths[p] + 1

        for (v in edges[u]) {
            travel(v, u)
        }
    }
}