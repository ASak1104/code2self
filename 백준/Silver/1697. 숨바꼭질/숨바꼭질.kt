import java.util.*
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    close()

    if (n >= k) return flush(n - k)

    val memo = IntArray(k shl 1)
    val queue = ArrayDeque<Int>()
    memo[n] = 1
    queue.addLast(n)

    while (memo[k] == 0) {
        val curr = queue.removeFirst()
        val cnt = memo[curr] + 1

        for (i in 0..2) {
            val next = when (i) {
                0 -> curr - 1
                1 -> curr + 1
                else -> curr shl 1
            }
            if (next in memo.indices && memo[next] == 0) {
                memo[next] = cnt
                queue.addLast(next)
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