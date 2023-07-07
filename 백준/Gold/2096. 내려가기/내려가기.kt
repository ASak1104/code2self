import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val winMins = IntArray(3) { readInt() }
    val winMaxs = winMins.copyOf()
    val mins = winMins.copyOf()
    val maxs = winMins.copyOf()

    repeat(n - 1) {
        repeat(3) { i ->
            winMins[i] = mins[i]
            winMaxs[i] = maxs[i]

            mins[i] = readInt()
            maxs[i] = mins[i]
        }

        for (c in 0..2) {
            mins[c] += when (c) {
                0 -> minOf(winMins[0], winMins[1])
                2 -> minOf(winMins[1], winMins[2])
                else -> winMins.min()
            }

            maxs[c] += when (c) {
                0 -> maxOf(winMaxs[0], winMaxs[1])
                2 -> maxOf(winMaxs[1], winMaxs[2])
                else -> winMaxs.max()
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("${maxs.max()} ${mins.min()}")
        flush()
        close()
    }
}