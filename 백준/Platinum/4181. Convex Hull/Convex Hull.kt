import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.sign

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = mutableListOf<Point>()

    repeat(n) {
        val (p, c) = StringTokenizer(readLine()).run {
            Point(nextToken().toLong(), nextToken().toLong()) to nextToken()
        }

        if (c == "Y") points.add(p)
    }
    close()

    points.sort()
    val lower = monotoneChain(points)

    points.reverse()
    val upper = monotoneChain(points)

    with(System.out.bufferedWriter()) {
        append("${lower.size + upper.size}\n")
        lower.forEach { append("$it\n") }
        upper.forEach { append("$it\n") }
        flush()
        close()
    }
}

fun monotoneChain(points: List<Point>): ArrayDeque<Point> {
    val stack = ArrayDeque<Point>(points.size)

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
    stack.removeLast()

    return stack
}

fun ccw(p: Point, p1: Point, p2: Point): Int {
    val ret = (p1.x - p.x) * (p2.y - p.y) - (p2.x - p.x) * (p1.y - p.y)
    return ret.sign
}

class Point(val x: Long, val y: Long): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return when (x == other.x) {
            true -> y.compareTo(other.y)
            else -> x.compareTo(other.x)
        }
    }

    override fun toString() = "$x $y"
}