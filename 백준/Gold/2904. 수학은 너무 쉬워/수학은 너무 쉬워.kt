import java.util.*
import kotlin.math.sqrt

const val MAX = 1e6.toInt()

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val nums = StringTokenizer(br.readLine()).run {
        IntArray(n) { nextToken().toInt() }
    }

    val sqrt = sqrt(nums.max().toDouble()).toInt()
    val primes = BooleanArray(MAX + 1) { true }
    val counts = IntArray(MAX + 1)
    val factors = Array(n) { HashMap<Int, Int>() }

    for (p in 2..sqrt) {
        if (!primes[p]) continue

        for (np in p * p..MAX step p) {
            primes[np] = false
        }

        for (i in 0 until n) {
            while (nums[i] % p == 0) {
                nums[i] /= p
                counts[p]++
                factors[i][p] = (factors[i][p] ?: 0) + 1
            }
        }
    }

    var pMax = sqrt

    for (i in 0 until n) {
        counts[nums[i]]++
        pMax = maxOf(pMax, nums[i])
    }

    var res = 1
    var count = 0

    for (p in 2..pMax) {
        val target = counts[p] / n

        if (target < 1) continue

        for (u in 0 until n) {
            if (p !in factors[u] || factors[u][p]!! <= target) continue

            for (v in 0 until n) {
                while (factors[u][p]!! > target && (factors[v][p] ?: 0) < target) {
                    factors[u][p] = factors[u][p]!! - 1
                    factors[v][p] = (factors[v][p] ?: 0) + 1
                    count++
                }
            }
        }

        res *= pow(p, target)
    }

    println("$res $count")
    br.close()
}

fun pow(a: Int, b: Int): Int {
    var ret = 1
    var x = a
    var y = b

    while (y > 0) {
        if (y and 1 == 1) {
            ret *= x
        }

        x *= x
        y = y ushr 1
    }

    return ret
}