import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val k = readInt()
    val coins = IntArray(n) { readInt() }
    val memo = IntArray(k + 1)

    memo[0] = 1

    for (c in coins) {
        for (s in 0 until k) {
            if (s + c <= k) {
                memo[s + c] += memo[s]
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("${memo[k]}").flush()
        close()
    }
}