import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.sign

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = mutableListOf<Point>()

    repeat(n) {
        val (p, c) = StringTokenizer(readLine()).run {
            Point(nextToken().toInt(), nextToken().toInt()) to nextToken()
        }

        if (c == "Y") points.add(p)
    }
    close()

    // Graham Scan

    with(points) {
        val a = minWith(compareBy(Point::x, Point::y))

        sortWith { b, c ->
            val ccw = ccw(a, b, c)
            when (a) {
                b -> -1
                c -> 1
                else -> when (ccw) {
                    0 -> (a to b).compareTo(a to c) * if (a.y < b.y) -1 else 1
                    else -> -ccw
                }
            }
        }
    }

    val stack = ArrayDeque<Point>()

    for (next in points) {
        while (stack.size > 1) {
            val second = stack.removeLast()
            val first = stack.last()

            if (ccw(first, second, next) >= 0) {
                stack.addLast(second)
                break
            }
        }
        stack.addLast(next)
    }

    with(System.out.bufferedWriter()) {
        append("${stack.size}\n")
        stack.forEach { append("$it\n") }
        flush()
        close()
    }
}

fun ccw(p: Point, p1: Point, p2: Point): Int {
    val ret = (p1.x - p.x).toLong() * (p2.y - p.y) - (p2.x - p.x).toLong() * (p1.y - p.y)
    return ret.sign
}

class Point(val x: Int, val y: Int) {
    infix fun to(o: Point): Long {
        return (x - o.x).toLong() * (x - o.x) + (y - o.y).toLong() * (y - o.y)
    }

    override fun toString() = "$x $y"
}