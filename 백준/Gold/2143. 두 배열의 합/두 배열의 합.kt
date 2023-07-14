import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val t = readInt()
    val n = readInt()
    val arr1 = IntArray(n) { readInt() }
    val m = readInt()
    val arr2 = IntArray(m) { readInt() }

    val sumMap = mutableMapOf<Int, Int>()

    for (i in 0 until n) {
        var sum = 0

        for (j in i until n) {
            sum += arr1[j]

            sumMap[sum] = (sumMap[sum] ?: 0) + 1
        }
    }

    var res = 0L

    for (i in 0 until m) {
        var sum = 0

        for (j in i until m) {
            sum += arr2[j]

            res += sumMap[t - sum] ?: 0
        }
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}