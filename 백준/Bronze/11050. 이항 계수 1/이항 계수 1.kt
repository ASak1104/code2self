import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run {
        nextToken().toInt() to nextToken().toInt()
    }

    close()

    with(System.out.bufferedWriter()) {
        val res = factorial(n) / (factorial(n - k) * factorial(k))

        append(res.toString())
        flush()
        close()
    }
}

fun factorial(x: Int): Int {
    var ret = 1

    for (i in 2..x) {
        ret *= i
    }

    return ret
}