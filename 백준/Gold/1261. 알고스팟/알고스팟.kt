import java.util.*

val maze = ArrayList<String>(101)
val visit = Array(101) { BooleanArray(101) }
val weights = arrayOf(0 to 1, 1 to 0, -1 to 0, 0 to -1)

var n = 0
var m = 0

fun main() = with(System.`in`.bufferedReader()) {
    with(StringTokenizer(readLine())) {
        m = nextToken().toInt()
        n = nextToken().toInt()
    }

    repeat(n) { maze += readLine() }

    println(escape())
    close()
}

fun escape(): Int {
    val pq = PriorityQueue(compareBy(Point::cost))

    pq.add(Point(0, 0, 0))
    visit[0][0] = true

    while (pq.isNotEmpty()) {
        val u = pq.poll()

        for ((rw, cw) in weights) {
            val v = Point(u.r + rw, u.c + cw, u.cost)

            if (!v.isValid()) continue

            if (v.isEnd()) return v.cost

            if (maze[v.r][v.c] == '1') v.cost++

            pq.add(v)
            visit[v.r][v.c] = true
        }
    }

    return 0
}

data class Point(val r: Int, val c: Int, var cost: Int) {

    fun isValid() = r in 0 until n && c in 0 until m && !visit[r][c]

    fun isEnd() = r == n - 1 && c == m - 1
}
