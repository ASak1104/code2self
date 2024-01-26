import java.util.*
import kotlin.math.sqrt

const val PRIME_BOUNDARY = 1e7.toInt()

val isPrimes = BooleanArray(PRIME_BOUNDARY) { true }
val primes = ArrayList<Int>(446)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine().toInt()
    val br = System.out.bufferedWriter()

    setPrimes()

    repeat(tc) {
        val m = readLine().toInt()
        val sizes = StringTokenizer(readLine()).run { IntArray(m) { nextToken().toInt() } }
        val minPrime = getScenario(sizes)

        br.append("Scenario ${it + 1}:\n$minPrime\n\n")
    }

    br.flush()
    br.close()
    close()
}

fun setPrimes() {
    val square = sqrt(PRIME_BOUNDARY.toDouble()).toInt()

    for (p in 2..square) {
        if (!isPrimes[p]) {
            continue
        }

        for (np in p * p until PRIME_BOUNDARY step p) {
            isPrimes[np] = false
        }
    }

    for (p in 2 until PRIME_BOUNDARY) {
        if (!isPrimes[p]) {
            continue
        }

        primes += p
    }
}

fun getScenario(sizes: IntArray): Int {
    val counts = HashMap<Int, Int>()

    for (i in 2 until PRIME_BOUNDARY) {
        for (size in sizes) {
            if (i + size > primes.size) {
                continue
            }

            val sum = simulate(i, size)

            if (sum >= PRIME_BOUNDARY || !isPrimes[sum]) {
                continue
            }

            counts[sum] = (counts[sum] ?: 0) + 1

            if (counts[sum] == sizes.size) {
                return sum
            }
        }
    }

    return -1
}

fun simulate(start: Int, size: Int): Int {
    var sum = 0

    for (i in start until start + size) {
        sum += primes[i]
    }

    return sum
}
