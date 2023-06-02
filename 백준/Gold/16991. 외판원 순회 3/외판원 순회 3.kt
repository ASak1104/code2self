import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val cities = Array(n) {
        StringTokenizer(readLine()).run { Point(nextToken().toDouble(), nextToken().toDouble()) }
    }
    val costs = Array(n) { DoubleArray(n) }
    for (r in 0 until n) {
        for (c in 0 until n) {
            if (r == c) continue
            
            val cost = cities[r] to cities[c]
            costs[r][c] = cost
            costs[c][r] = cost
        }
    }
    val tsp = TSP(n, costs)
    print(tsp.travel(1, 0))
    close()
}

class TSP(val n: Int, val costs: Array<DoubleArray>) {
    val MAX_VALUE = 64001.0
    val memo = Array(1 shl n) { DoubleArray(n) }

    fun travel(mask: Int, u: Int): Double {
        if (mask == memo.lastIndex) {
            return costs[u][0]
        }
        if (memo[mask][u] > 0) {
            return memo[mask][u]
        }
        memo[mask][u] = MAX_VALUE
        
        for (v in 0 until n) {
            val bit = 1 shl v
            if (mask and bit == bit) continue

            val nextCost = costs[u][v] + travel(mask or bit, v)
            
            if (nextCost < memo[mask][u]) {
                memo[mask][u] = nextCost
            }
        }
        return memo[mask][u]
    }
}

data class Point(val x: Double, val y: Double) {
    infix fun to(o: Point) = sqrt((x - o.x).pow(2) + (y - o.y).pow(2))
}