import java.util.*

val maze = Array(101) { IntArray(101) }
val visit = Array(101) { BooleanArray(101) }
val weights = arrayOf(0 to 1, 1 to 0, -1 to 0, 0 to -1)

var n = 0
var m = 0

fun main() = with(System.`in`.bufferedReader()) {
    with(StringTokenizer(readLine())) {
        m = nextToken().toInt()
        n = nextToken().toInt()
    }

    for (r in 0 until n) {
        val string = readLine()

        for (c in 0 until m) {
            if (string[c] == '1') {
                maze[r][c] = 1
            }
        }
    }

    val res = escape()

    println(res)
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

            pq.add(v)
            visit[v.r][v.c] = true
        }
    }

    return 0
}

class Point(val r: Int, val c: Int, cost: Int) {

    var cost = 0

    init {
        this.cost = cost

        if (isValid() && maze[r][c] == 1) {
            this.cost++
        }
    }

    fun isValid() = r in 0 until n && c in 0 until m && !visit[r][c]

    fun isEnd() = r == n - 1 && c == m - 1
}
