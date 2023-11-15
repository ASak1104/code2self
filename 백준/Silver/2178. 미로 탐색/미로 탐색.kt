import java.util.*
import kotlin.collections.ArrayDeque

val weights = arrayOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

lateinit var maze: Array<String>
lateinit var visit: Array<BooleanArray>

var n = 0
var m = 0

fun main() {
    val br = System.`in`.bufferedReader();

    with(StringTokenizer(br.readLine())) {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    maze = Array(n) { br.readLine() }
    visit = Array(n) { BooleanArray(m) }

    val res = travel(0, 0)

    println(res)
    br.close()
}

fun travel(sr: Int, sc: Int): Int {
    val deque = ArrayDeque<Point>(n * m)

    deque.addLast(Point(sr, sc, 1))
    visit[sr][sc] = true

    while (deque.isNotEmpty()) {
        val u = deque.removeFirst()

        if (u.isEnd()) {
            return u.cost
        }

        for ((rw, cw) in weights) {
            val v = Point(u.r + rw, u.c + cw, u.cost + 1)

            if (v.isValid()) {
                deque.addLast(v)
                visit[v.r][v.c] = true
            }
        }
    }

    return -1
}

data class Point(val r: Int, val c: Int, val cost: Int) {
    fun isValid(): Boolean {
        if (r !in 0 until n || c !in 0 until m) {
            return false
        }

        return !visit[r][c] && maze[r][c] == '1'
    }

    fun isEnd(): Boolean {
        return r == n - 1 && c == m - 1
    }
}
