import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array(n) { IntArray(m) }
    val visit = Array(n) { BooleanArray(m) }
    val finish = Array(n) { BooleanArray(m) }
    val moves = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

    for (r in 0 until n) {
        val line = readLine()

        for (c in 0 until m) {
			edges[r][c] = when (line[c]) {
                'U' -> 0
                'D' -> 1
                'L' -> 2
                else -> 3
            }
        }
    }

    var count = 0

    fun travel(r: Int, c: Int) {
        visit[r][c] = true

        val pair = moves[edges[r][c]]
        val vr = r + pair.first
        val vc = c + pair.second

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