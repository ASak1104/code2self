import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val coords = Array(n) {
        val line = StringTokenizer(readLine())
        line.nextToken().toDouble() to line.nextToken().toDouble()
    }
    close()

    val visit = BooleanArray(n)
    val heap = PriorityQueue<Pair<Double, Int>>(compareBy { it.first })
    heap.add(0.0 to 0)
    var net = n
    var result = 0.0

    while (net > 0) {
        val (dist, u) = heap.poll()
        if (visit[u]) continue
        visit[u] = true
        result += sqrt(dist)
        net--

        for (v in 1 until n) {
            if (visit[v]) continue
            val (ux, uy) = coords[u]
            val (vx, vy) = coords[v]
            heap.add((ux - vx).pow(2) + (uy - vy).pow(2) to v)
        }
    }
    print(result)
}