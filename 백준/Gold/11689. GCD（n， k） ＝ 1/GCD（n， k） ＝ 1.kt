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
    fun query(n: Long): Long {
        if (n < 3) return 1

        if (isPrime(n)) return n - 1

        val primes = arrayListOf<Long>()

        for (k in 2..sqrt(n.toDouble()).toLong()) {
            if (n % k > 0) continue

            if (isRelative(k, n / k)) return query(k) * query(n / k)

            if (isPrime(k)) primes.add(k)
        }

        var ret = n

        for (k in primes) {
            ret *= (k - 1)
            ret /= k
        }
        return ret
    }

    fun isRelative(a: Long, b: Long): Boolean {
        for (k in 2..a) {
            if (a % k == 0L && b % k == 0L) return false
        }
        return true
    }

    fun isPrime(n: Long): Boolean {
        for (k in 2..sqrt(n.toDouble()).toInt()) {
            if (n % k == 0L) return false
        }
        return true
    }
}