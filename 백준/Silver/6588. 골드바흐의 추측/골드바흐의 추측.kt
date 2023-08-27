const val MAX = 1e6.toInt()
const val MAX_SQRT = 1000

val primes = BooleanArray(MAX + 1) { true }
var n = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    for (p in 2..MAX_SQRT) {
        if (!primes[p]) continue

        for (np in p * p..MAX step p) {
            primes[np] = false
        }
    }

    outer@ while (true) {
        n = br.readLine().toInt()

        if (n == 0) break@outer

        for (p in 2..n / 2) {
            if (!primes[p]) continue

            val q = n - p

            if (primes[q]) {
                bw.append("$n = $p + $q\n")
                continue@outer
            }
        }

        bw.append("Goldbach's conjecture is wrong.\n")
    }

    bw.flush()
    bw.close()
    br.close()
}