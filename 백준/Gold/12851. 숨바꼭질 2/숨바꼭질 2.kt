import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    close()

    if (n >= k) return flush(n - k, 1)

    val memo = IntArray(min(k shl 1, 100_001))
    val queue = ArrayDeque<Int>(memo.size)
    var res = 0

    memo[n] = 1
    queue.addLast(n)

    while (queue.isNotEmpty()) {
        val curr = queue.removeFirst()
        val cnt = memo[curr] + 1

        if (memo[k] in 1 until cnt) break

        for (i in 0 until 3) {
            val next = when (i) {
                0 -> curr - 1
                1 -> curr + 1
                else -> curr shl 1
            }
            if (next in memo.indices) {
                if (memo[next] == 0) memo[next] = cnt

                if (cnt == memo[next]) queue.addLast(next)

                if (next == k) res++
            }
        }
    }
    flush(memo[k] - 1, res)
}

fun flush(t: Int, cnt: Int) = with(System.out.bufferedWriter()) {
    append("$t\n$cnt")
    flush()
    close()
}