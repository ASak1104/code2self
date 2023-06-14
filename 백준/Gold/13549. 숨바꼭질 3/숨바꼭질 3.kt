import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    close()

    if (n >= k) return flush(n - k)

    val memo = IntArray(min(k shl 1, 100_001)) { Int.MAX_VALUE }
    val queue = ArrayDeque<Int>(memo.size)

    memo[n] = 0
    queue.addLast(n)

    while (memo[k] == Int.MAX_VALUE) {
        val curr = queue.removeFirst()

        for (i in 0 until 3) {
            val cnt = memo[curr] + (if (i < 2) 1 else 0)
            val next = when (i) {
                0 -> curr - 1
                1 -> curr + 1
                else -> curr shl 1
            }
            if (next in memo.indices && cnt < memo[next]) {
                memo[next] = cnt
                queue.addLast(next)
            }
        }
    }
    flush(memo[k])
}

fun flush(t: Int) = with(System.out.bufferedWriter()) {
    append("$t")
    flush()
    close()
}