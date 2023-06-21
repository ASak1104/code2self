import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, x) = StringTokenizer(readLine()).run {
        Triple(nextToken().toInt(), nextToken().toInt(), nextToken().toInt() - 1)
    }
    val edges = Array(n) { ArrayList<Edge>() }
    val reverseEdge = Array(n) { ArrayList<Edge>() }

    repeat(m) {
        with(StringTokenizer(readLine())) {
            val u = nextToken().toInt() - 1
            val v = nextToken().toInt() - 1
            val c = nextToken().toInt()

            edges[u].add(Edge(v, c))
            reverseEdge[v].add(Edge(u, c))
        }
    }

    val dist = dijkstra(edges, x)
    val reverseDist = dijkstra(reverseEdge, x)
    var res = 0

    for (u in dist.indices) {
        val t = dist[u] + reverseDist[u]

        if (res < t) res = t
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
    close()
}

fun dijkstra(edges: Array<ArrayList<Edge>>, x: Int): IntArray {
    val dist = IntArray(edges.size) { Int.MAX_VALUE }
    val pq = PriorityQueue(dist.size, compareBy(Edge::c))

    dist[x] = 0
    pq.add(Edge(x, 0))

    while (pq.isNotEmpty()) {
        val (u, d) = pq.poll()

        for ((v, c) in edges[u]) {
            val dc = d + c

            if (dist[v] > dc) {
                dist[v] = dc
                pq.add(Edge(v, dc))
            }
        }
    }

    return dist
}

data class Edge(val v: Int, val c: Int)