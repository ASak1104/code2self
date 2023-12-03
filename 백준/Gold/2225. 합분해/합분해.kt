import java.util.*

const val REMAINDER = 1e9.toInt()

val memo = Array(201) { IntArray(201) }

var n = 0
var k = 0

fun main() = with(System.`in`.bufferedReader()) {
    with(StringTokenizer(readLine())) {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }

    println(topDown(n, k))
    close()
}


fun topDown(n: Int, k: Int): Int {
    if (n == 0 || k == 1) {
        return 1
    }

    if (memo[n][k] != 0) {
        return memo[n][k]
    }

    var ret = 0

    for (x in 0..n) {
        ret = (ret + topDown(x, k - 1)) % REMAINDER
    }

    memo[n][k] = ret

    return ret
}
