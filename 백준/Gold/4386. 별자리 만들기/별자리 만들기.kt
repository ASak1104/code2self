import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val coords = Array(n) { readLine().split(' ').map(String::toDouble) }
    close()

    val visit = BooleanArray(n)
    val heap = PriorityQueue<Star> { a, b -> a.dist.compareTo(b.dist) }
    heap.add(Star(0.0, 0))
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
            heap.add(Star(dist(coords[u], coords[v]), v))
        }
    }
    print(result)
}

fun dist(u: List<Double>, v: List<Double>) = (v[0] - u[0]).pow(2) + (v[1] - u[1]).pow(2)

data class Star(val dist: Double, val idx: Int)