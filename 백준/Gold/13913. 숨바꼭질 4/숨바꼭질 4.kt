import java.util.*
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    close()

    if (n >= k) return flush(n - k, n downTo k)

    val memo = IntArray(min(k shl 1, 100_001)) { -1 }
    val queue = IntArray(memo.size + 1)
    var front = 0
    var rear = 0

    memo[n] = 0
    queue[rear++] = n

    while (memo[k] == -1) {
        val curr = queue[front++]
        val cnt = memo[curr] + 1

        for (i in 0 until 3) {
            val next = when (i) {
                0 -> curr - 1
                1 -> curr + 1
                else -> curr shl 1
            }
            if (next in memo.indices && memo[next] == -1) {
                memo[next] = cnt
                queue[rear++] = next

                if (next == k) break
            }
        }
    }
    val ints = MutableList(memo[k] + 1) { k }
    var idx = memo[k] - 1
    var prev = k

    for (j in rear - 1 downTo 0) {
        val it = queue[j]

        if (memo[it] != idx) continue

        if (it == prev - 1 || it == prev + 1 || it shl 1 == prev) {
            ints[idx--] = it
            prev = it
        }
    }
    flush(memo[k], ints)
}

fun flush(t: Int, ints: Iterable<Int>) = with(System.out.bufferedWriter()) {
    append("$t\n")
    for (e in ints) {
        append("$e ")
    }
    flush()
    close()
}