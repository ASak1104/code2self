import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = MutableList(n) {
        StringTokenizer(readLine()).run {
            val home = nextToken().toInt()
            val office = nextToken().toInt()
            Point(min(home, office), max(home, office))
        }
    }
    val d = readLine().toInt()
    points.sortBy(Point::e)

    val pq = PriorityQueue<Int>()
    var res = 0

    for (point in points) {
        if (point.d > d) continue

        val s = point.e - d
        
        while (pq.isNotEmpty() && pq.peek() < s) {
            pq.poll()
        }
        pq.add(point.s)
        res = max(res, pq.size)
    }
    print(res)
}

data class Point(val s: Int, val e: Int) {
    val d = e - s
}