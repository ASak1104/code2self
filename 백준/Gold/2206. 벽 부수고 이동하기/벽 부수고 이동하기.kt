import java.util.*
import kotlin.collections.ArrayDeque

val weights = arrayOf(1 at 0, -1 at 0, 0 at 1, 0 at -1)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val map = Array(n) { BooleanArray(m) }
    val counts = Array(n) { Array(m) { IntArray(2) { Int.MAX_VALUE } } }

    repeat(n) { r ->
        val row = readLine()

        repeat(m) { c ->
            map[r][c] = row[c] == '0'
        }
    }
    close()

    var res = -1
    val queue = ArrayDeque<Point>()

    queue.addLast(0 at 0)
    counts[0][0][0] = 1
    counts[0][0][1] = 1

    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()

        if (u.r == n - 1 && u.c == m - 1) {
            res = u.dist
            break
        }

        for (w in weights) {
            val v = u + w

            if (v.r !in 0 until n || v.c !in 0 until m) continue

            if (map[v.r][v.c]) {
                if (v.dist < counts[v.r][v.c][v.jump]) {
                    counts[v.r][v.c][v.jump] = v.dist
                    queue.addLast(v)
                }
            } else if (v.jump == 0) {
                v.jump = 1

                if (v.dist < counts[v.r][v.c][v.jump]) {
                    counts[v.r][v.c][v.jump] = v.dist
                    queue.addLast(v)
                }
            }
        }
    }

    with(System.out.bufferedWriter()) {
        append("$res").flush()
        close()
    }
}

infix fun Int.at(o: Int) = Point(this, o, 1, 0)

data class Point(val r: Int, val c: Int, val dist: Int, var jump: Int) {
    operator fun plus(o: Point) = Point(r + o.r, c + o.c, dist + o.dist, jump)
}