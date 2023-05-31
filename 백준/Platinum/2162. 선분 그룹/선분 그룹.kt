import java.util.*
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val vectors = MutableList(n) {
        val line = StringTokenizer(br.readLine())
        val p1 = Point(line.nextToken().toInt(), line.nextToken().toInt())
        val p2 = Point(line.nextToken().toInt(), line.nextToken().toInt())
        Vector(p1, p2)
    }
    br.close()

    val edges = Array(n) { BooleanArray(n) }
    val visit = BooleanArray(n)
    for (u in 0 until n) {
        for (v in 0 until n) {
            edges[u][v] = vectors[u].isCross(vectors[v])
        }
    }

    var count = 0
    var maxNodes = 0
    for (u in 0 until n) {
        if (!visit[u]) {
            maxNodes = max(maxNodes, travel(edges, visit, u))
            count++
        }
    }
    print("$count\n$maxNodes")
}

fun travel(edges: Array<BooleanArray>, visit: BooleanArray, u: Int): Int {
    visit[u] = true
    var nodes = 1
    for (v in edges[u].indices) {
        if (!visit[v] && edges[u][v]) {
            nodes += travel(edges, visit, v)
        }
    }
    return nodes
}

data class Point(val x: Int, val y: Int)

class Vector(val p1: Point, val p2: Point) {
    private val minX = min(p1.x, p2.x)
    private val maxX = max(p1.x, p2.x)
    private val minY = min(p1.y, p2.y)
    private val maxY = max(p1.y, p2.y)

    private fun ccw(p: Point): Int {
        var res = p.x * p1.y + p1.x * p2.y + p2.x * p.y
        res -= p.y * p1.x + p1.y * p2.x + p2.y * p.x
        return res.sign
    }

    private fun ccw(v: Vector) = ccw(v.p1) * ccw(v.p2)

    fun isCross(o: Vector): Boolean {
        if (ccw(o) == 0 && o.ccw(this) == 0) {
            return p1 in o || p2 in o || o.p1 in this || o.p2 in this
        }
        return ccw(o) <= 0 && o.ccw(this) <= 0
    }

    operator fun contains(p: Point) = p.x in minX..maxX && p.y in minY..maxY
}