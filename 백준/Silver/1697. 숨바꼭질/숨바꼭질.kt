import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    close()

    if (n >= k) return flush(n - k)

    val memo = IntArray(min(k shl 1, 100_001))
    val queue = IntArray(memo.size)
    var front = 0
    var rear = 0

    memo[n] = 1
    queue[rear++] = n

    while (memo[k] == 0) {
        val curr = queue[front++]
        val cnt = memo[curr] + 1

        for (i in 0 until 3) {
            val next = when (i) {
                0 -> curr - 1
                1 -> curr + 1
                else -> curr shl 1
            }
            if (next in memo.indices && memo[next] == 0) {
                memo[next] = cnt
                queue[rear++] = next
            }
        }
    }
    flush(memo[k] - 1)
}

fun flush(ret: Int) = with(System.out.bufferedWriter()) {
    append(ret.toString())
    flush()
    close()
}