import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toLong()
    val epf = EulerProductFormula()

    val res = epf.query(n)

    with(System.out.bufferedWriter()) {
        append("$res").flush()
        close()
    }
}

class EulerProductFormula {
    val memo = mutableMapOf(1L to 1L)

    fun query(n: Long): Long {
        if (n in memo) return memo[n]!!

        if (isPrime(n)) {
            memo[n] = n - 1
            return n - 1
        }

        val bound = sqrt(n.toDouble()).toLong()
        val primes = arrayListOf<Long>()

        for (k in 2..bound) {
            if (n % k > 0) continue

            if (isRelative(k, n / k)) {
                val kq = query(k)
                val nkq = query(n / k)

                memo[k] = kq
                memo[n / k] = nkq

                return kq * nkq
            }

            if (isPrime(k)) primes.add(k)
        }

        var ret = n

        for (k in primes) {
            ret *= (k - 1)
            ret /= k
        }

        memo[n] = ret

        return ret
    }

    fun isRelative(a: Long, b: Long): Boolean {
        if (a > b) return isRelative(b, a)

        for (k in 2..a) {
            if (a % k == 0L && b % k == 0L) return false
        }
        return true
    }

    fun isPrime(n: Long): Boolean {
        val bound = sqrt(n.toDouble()).toInt()

        for (k in 2..bound) {
            if (n % k == 0L) return false
        }
        return true
    }
}