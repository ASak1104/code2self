import java.io.StreamTokenizer

const val MAX_VALUE = 500_000
val sttk = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(sttk) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val h = readInt()

    val DU = IntArray(MAX_VALUE + 1)
    val UD = IntArray(MAX_VALUE + 1)

    repeat(n ushr 1) {
        DU[readInt()]++
        UD[h - readInt() + 1]++
    }

    for (i in 1..h) {
        UD[i] += UD[i - 1]
        DU[h - i] += DU[h - i + 1]
    }

    var min = n
    var count = 0

    for (i in 1..h) {
        val sum = DU[i] + UD[i]

        if (sum < min) {
            min = sum
            count = 1
        } else if (sum == min) {
            count++
        }
    }

    print("$min $count")
}