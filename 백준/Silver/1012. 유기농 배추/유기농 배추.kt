import java.util.*
import kotlin.collections.ArrayDeque

val br = System.`in`.bufferedReader()

fun main() {
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()

    repeat(t) {
        val (m, n, k) = StringTokenizer(br.readLine()).run {
            val readInt = { nextToken().toInt() }
            Triple(readInt(), readInt(), readInt())
        }
        val farm = Farm(n, m, k)

        farm.solve()

        bw.append("${farm.worm}\n")
    }
    br.close()

    bw.flush()
    bw.close()
}

class Farm(n: Int, m: Int, k: Int) {
    val field = Array(n) { BooleanArray(m) }
    val moves = arrayOf(1 at 0, -1 at 0, 0 at 1, 0 at -1)
    var worm = 0

    init {
        repeat(k) {
            with(StringTokenizer(br.readLine())) {
                val c = nextToken().toInt()
                val r = nextToken().toInt()

                field[r][c] = true
            }
        }
    }

    fun solve() {
        for (r in field.indices) {
            for (c in field[r].indices) {
                if (field[r][c]) {
                    field[r][c] = false
                    travel(r, c)
                    worm++
                }
            }
        }
    }

    fun travel(sr: Int, sc: Int) {
        val queue = ArrayDeque<Point>()
        queue.addLast(sr at sc)

        while (queue.isNotEmpty()) {
            val u = queue.removeFirst()

            for (m in moves) {
                val v = u + m

                if (v.valid()) {
                    field[v.r][v.c] = false
                    queue.addLast(v)
                }
            }
        }
    }

    fun Point.valid() = r in field.indices && c in field[0].indices && field[r][c]

    infix fun Int.at(o: Int) = Point(this, o)

    class Point(val r: Int, val c: Int) {
        operator fun plus(o: Point) = Point(r + o.r, c + o.c)
    }
}