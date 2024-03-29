import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val points = MutableList(n) {
        StringTokenizer(readLine()).run {
            val home = nextToken().toInt()
            val office = nextToken().toInt()

            if (home > office)
                Point(office, home)
            else
                Point(home, office)
        }
    }
    val d = readLine().toInt()
    close()

    points.sortBy(Point::e)

    val pq = PriorityQueue<Int>()
    var res = 0

    points.forEach {
        if (it.e - it.s > d) return@forEach

        val s = it.e - d

        while (pq.isNotEmpty() && pq.peek() < s) pq.poll()

        pq.add(it.s)

        if (pq.size > res) res = pq.size
    }
    with(System.out.bufferedWriter()) {
        append(res.toString())
        flush()
        close()
    }
}

data class Point(val s: Int, val e: Int)