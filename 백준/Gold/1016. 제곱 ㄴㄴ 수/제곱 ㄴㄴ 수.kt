import java.io.StreamTokenizer
import kotlin.math.sqrt

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readLong = {
        nextToken()
        nval.toLong()
    }
    val min = readLong()
    val max = readLong()
    val nonPrimes = BooleanArray(sqrt(max.toDouble()).toInt() + 1)

    with(nonPrimes) {
        val maxPrime = sqrt(lastIndex.toDouble()).toInt()
        var prime = 2

        while (prime <= maxPrime) {
            val nonPrime = prime * prime

            for (k in nonPrime..lastIndex step prime) {
                this[k] = true
            }
            do { prime++ } while (this[prime])
        }
    }

    val primes = ArrayList<Int>(78498)
    val minToMaxNums = BooleanArray((max - min + 1).toInt())

    for (i in 2..nonPrimes.lastIndex) {
        if (!nonPrimes[i]) primes.add(i)
    }

    for (k in primes) {
        val square = 1L * k * k
        var start = min / square * square

        if (start < min) start += square

        for (unSquare in start..max step square) {
            minToMaxNums[(unSquare - min).toInt()] = true
        }
    }

    with(System.out.bufferedWriter()) {
        append("${minToMaxNums.count { !it }}")
        flush()
        close()
    }
}