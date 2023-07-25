import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()
    val seq = IntArray(n) { readInt() }

    var res = 0
    var s = 0
    var e = 0
    var sum = 0

    while (s < n && e < n) {
        while (e < n && sum < m) {
            sum += seq[e++]
        }

        while (s < n && sum >= m) {
            if (sum == m) res++

            sum -= seq[s++]
        }
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}