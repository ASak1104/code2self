import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() = with(System.`in`.bufferedReader()) {
    val v1 = Vector(readLine().split(' ').map(String::toLong))
    val v2 = Vector(readLine().split(' ').map(String::toLong))
    close()

    if (v1.ccw(v2) == 0 && v2.ccw(v1) == 0) {
        if (v2.p1 in v1 || v2.p2 in v1 || v1.p1 in v2 || v1.p2 in v2) {
            return print(1)
        }
        return print(0)
    }
    if (v1.ccw(v2) <= 0 && v2.ccw(v1) <= 0) {
        return print(1)
    }
    print(0)
}

class Vector(array: List<Long>) {
    val p1 = Point(array[0], array[1])
    val p2 = Point(array[2], array[3])

    fun ccw(v: Vector) = ccw(v.p1) * ccw(v.p2)

    fun ccw(p0: Point): Int {
        var calc = p0.x * p1.y + p1.x * p2.y + p2.x * p0.y
        calc -= p0.y * p1.x + p1.y * p2.x + p2.y * p0.x
        return calc.sign
    }

    operator fun contains(p: Point): Boolean {
        if (p == p1 || p == p2) {
            return true
        }
        if (p.x !in min(p1.x, p2.x)..max(p1.x, p2.x)) {
            return false
        }
        if (p.y !in min(p1.y, p2.y)..max(p1.y, p2.y)) {
            return false
        }
        return (p1.y - p.y) * (p.x - p2.x) == (p.y - p2.y) * (p1.x - p.x)
    }

    data class Point(val x: Long, val y: Long)
}