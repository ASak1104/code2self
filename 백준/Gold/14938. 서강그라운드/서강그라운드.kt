import java.util.*

const val MAX = 1e9.toInt()

var n = 0
var m = 0
var r = 0

lateinit var items: IntArray
lateinit var edges: Array<ArrayList<Pair<Int, Int>>>

fun main() = with(System.`in`.bufferedReader()) {
    with(StringTokenizer(readLine())) {
        n = nextToken().toInt()
        m = nextToken().toInt()
        r = nextToken().toInt()
    }

    items = StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } }
    edges = Array(n) { arrayListOf() }

    repeat(r) {
        val st = StringTokenizer(readLine())
        val u = st.nextToken().toInt() - 1
        val v = st.nextToken().toInt() - 1
        val w = st.nextToken().toInt()

        edges[u] += v to w
        edges[v] += u to w
    }

    println(findOptimalSpot())
    close()
}

fun findOptimalSpot(): Int {
    val cumulatedItems = IntArray(n)

    for (start in 0 until n) {
        val dists = IntArray(n) { MAX }
        val pq = PriorityQueue(compareBy(Pair<Int, Int>::second))

        dists[start] = 0
        pq += start to 0

        while (pq.isNotEmpty()) {
            val (u, cost) = pq.poll()

            for ((v, w) in edges[u]) {
                if (cost + w > m || dists[v] <= cost + w) {
                    continue
                }

                dists[v] = cost + w
                pq += v to dists[v]
            }
        }

        for (node in 0 until n) {
            if (dists[node] == MAX) {
                continue
            }

            cumulatedItems[node] += items[start]
        }
    }

    return cumulatedItems.max()
}
