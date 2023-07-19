import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = StringTokenizer(readLine()).run {
        nextToken().toLong() to nextToken().toLong()
    }

    val bc = BitCounter()

    val cbc = bc.cumulativeBitCount(b)
    val cbcPrev = bc.cumulativeBitCount(a - 1)

    with(System.out.bufferedWriter()) {
        append("${cbc - cbcPrev}")
        flush()
        close()
    }
}

class BitCounter {
    val memo = LongArray(55)

    init {
        memo[0] = 1L

        for (i in 1 until memo.size) {
            memo[i] = (1L shl i - 1) + (memo[i - 1] shl 1) - 1
        }
    }

    fun cumulativeBitCount(x: Long): Long {
        var count = 0L
        var target = x
        var bitCount = 0

        while (target > 0) {
            val msbIndex = getMsbIndex(target)
            val shift = 1L shl msbIndex

            count += bitCount * shift + memo[msbIndex]
            target = target xor shift
            bitCount++
        }

        return count
    }

    fun getMsbIndex(x: Long): Int {
        var shift = 0

        while ((1L shl shift) <= x) {
            shift++
        }

        return shift - 1
    }
}