import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val s = readInt()
    val seq = IntArray(n) { readInt() }

    for (i in 1 until n) {
        seq[i] += seq[i - 1]
    }

    var res = n + 1
    var left = 0
    var right = 0

    while (right < n && seq[right] < s) right++

    while (right < n) {
        while (seq[right] - seq[left] >= s) left++

        res = minOf(res, ++right - left)
    }

    if (res > n) res = 0

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}