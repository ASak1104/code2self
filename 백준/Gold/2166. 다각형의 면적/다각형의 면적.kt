import java.util.*
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = Array(n) {
        StringTokenizer(readLine()).run { Point(nextToken().toLong(), nextToken().toLong()) }
    }
    close()

    var area = 0L
    var i = points.lastIndex
    for (j in points.indices) {
        area += points[i] det points[j]
        i = j
    }
    print("%.1f".format(abs(area) / 2.0))
}

data class Point(val x: Long, val y: Long) {
    infix fun det(o: Point) = x * o.y - y * o.x
}