import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val w = readInt()
    val n = readInt()
    val seq = IntArray(n) { readInt() }
    val memo = BooleanArray(800_000)

    memo[seq[0] + seq[1]] = true

    for (k in 2..n - 2) {
        for (i in k + 1 until n) {
            val target = w - (seq[k] + seq[i])

            if (target > 0 && memo[target]) {
                return flush("YES")
            }
        }

        for (i in 0 until k) {
            memo[seq[i] + seq[k]] = true
        }
    }

    flush("NO")
}

fun flush(s: String) = with(System.out.bufferedWriter()) {
    append(s).flush()
    close()
}