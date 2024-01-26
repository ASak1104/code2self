import java.util.*
import kotlin.math.sqrt

const val PRIME_BOUNDARY = 1e7.toInt()

val isPrimes = BooleanArray(PRIME_BOUNDARY) { true }
val primeSums = ArrayList<Long>(664579)

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

    primeSums += 2

    for (p in 3 until PRIME_BOUNDARY) {
        if (!isPrimes[p]) {
            continue
        }

        primeSums += primeSums.last() + p
    }
}

fun getScenario(sizes: IntArray): Int {
    val counts = HashMap<Int, Int>()

    for (left in primeSums.indices) {
        for (size in sizes) {
            val right = left + size

            if (right > primeSums.size || primeSums[right] - primeSums[left] > PRIME_BOUNDARY) {
                continue
            }

            val sum = (primeSums[right] - primeSums[left]).toInt()

            if (!isPrimes[sum]) {
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
