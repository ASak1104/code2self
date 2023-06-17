import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.sign

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = MutableList(n) {
        StringTokenizer(readLine()).run { Point(nextToken().toInt(), nextToken().toInt())  }
    }
    close()

    var res = 0

    points.sort()
    res += monotoneChain(points)

    points.reverse()
    res += monotoneChain(points)

    with(System.out.bufferedWriter()) {
        append("$res").flush()
        close()
    }
}

fun monotoneChain(points: List<Point>): Int {
    val stack = ArrayDeque<Point>(points.size)

    for (next in points) {
        while (stack.size > 1) {
            val second = stack.removeLast()
            val first = stack.last()

            if (ccw(first, second, next) > 0) {
                stack.addLast(second)
                break
            }
        }
        stack.addLast(next)
    }
    return stack.size - 1
}

fun ccw(p0: Point, p1: Point, p2: Point): Int {
    val res = (p1.x - p0.x).toLong() * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y)
    return res.sign
}

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return when (x == other.x) {
            true -> y - other.y
            else -> x - other.x
        }
    }
}