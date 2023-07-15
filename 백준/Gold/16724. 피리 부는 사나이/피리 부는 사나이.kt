import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array<String>(n) { readLine() }
    val visit = Array(n) { BooleanArray(m) }
    val finish = Array(n) { BooleanArray(m) }

    var count = 0

    fun travel(r: Int, c: Int) {
        visit[r][c] = true

        val vr = when (edges[r][c]) {
            'U' -> r - 1
            'D' -> r + 1
            else -> r
        }
        val vc = when (edges[r][c]) {
            'L' -> c - 1
            'R' -> c + 1
            else -> c
        }

        if (!visit[vr][vc]) {
            travel(vr, vc)
        } else if (!finish[vr][vc]) {
            count++
        }

        finish[r][c] = true
    }

    for (r in 0 until n) {
        for (c in 0 until m) {
            if (!visit[r][c]) travel(r, c)
        }
    }

    with(System.out.bufferedWriter()) {
        append("$count")
        flush()
        close()
    }
}