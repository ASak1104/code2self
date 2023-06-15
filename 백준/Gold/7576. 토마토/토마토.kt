import java.util.*
import kotlin.collections.ArrayDeque

val br = System.`in`.bufferedReader()

fun main() {
    val (n, m) = StringTokenizer(br.readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val farm = Farm(m, n)

    var res = farm.travel()

    if (res == Int.MAX_VALUE) res = -1

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}

class Farm(val n: Int, val m: Int) {
    val weights = arrayOf(1 at 0, -1 at 0, 0 at 1, 0 at -1)
    val map = Array(n) { IntArray(m) { Int.MAX_VALUE } }
    val queue = ArrayDeque<Point>()

    init {
        repeat(n) { r ->
            val st = StringTokenizer(br.readLine())

            repeat(m) { c ->
                when (st.nextToken().toInt()) {
                    1 -> {
                        map[r][c] = 0
                        queue.addLast(Point(r, c, 0))
                    }
                    -1 -> {
                        map[r][c] = -1
                    }
                }
            }
        }
    }

    fun travel(): Int {
        while (queue.isNotEmpty()) {
            val u = queue.removeFirst()

            for (weight in weights) {
                val v = u + weight

                if (v.valid()) {
                    map[v.r][v.c] = v.w
                    queue.addLast(v)
                }
            }
        }
        return map.maxOf { it.max() }
    }

    infix fun Int.at(o: Int) = Point(this, o, 1)

    fun Point.valid() = r in 0 until n && c in 0 until m && this.w < map[r][c]

    data class Point(val r: Int, val c: Int, val w: Int) {
        operator fun plus(o: Point) = Point(r + o.r, c + o.c, w + o.w)
    }
}