import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val k = readInt()
    val coins = mutableSetOf<Int>()
    val memo = IntArray(k + 1) { k + 1 }

    repeat(n) { coins += readInt() }

    memo[0] = 0

    for (i in 0 until k) {
        for (c in coins) {
            if (i + c <= k && memo[i + c] > memo[i] + 1) {
                memo[i + c] = memo[i] + 1
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("${if (memo[k] > k) -1 else memo[k]}").flush()
        close()
    }
}