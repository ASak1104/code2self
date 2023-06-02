import java.util.*

val MAX_VALUE = 1_000_001

fun main() = with(System.`in`.bufferedReader()) {
    val (V, E) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array(V) { arrayListOf<Pair<Int, Int>>() }

    repeat(E) {
        StringTokenizer(readLine()).apply {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1
            val c = nextToken().toInt()
            edges[u].add(v to c)
            edges[v].add(u to c)
        }
    }

    val visit = BooleanArray(V)
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    pq.add(0 to 0)

    var weights = 0
    var nodes = 0

    while (nodes < V) {
        val (cost, u) = pq.poll()
        if (visit[u]) continue

        visit[u] = true
        weights += cost
        nodes++

        for ((v, w) in edges[u]) {
            if (visit[v] || w == MAX_VALUE) continue
            pq.add(w to v)
        }
    }
    print(weights)
}