import java.io.StreamTokenizer
import kotlin.math.sqrt

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readLong = {
        nextToken()
        nval.toLong()
    }
    val min = readLong()
    val max = readLong()
    val minToMax = BooleanArray((max - min + 1).toInt())

    for (p in 2..sqrt(max.toDouble()).toInt()) {
        val sq = 1L * p * p

        val start = if (min % sq == 0L)
            min
        else
            min / sq * sq + sq

        for (unSq in start..max step sq) {
            minToMax[(unSq - min).toInt()] = true
        }
    }

    with(System.out.bufferedWriter()) {
        append("${minToMax.count { !it }}")
        flush()
        close()
    }
}