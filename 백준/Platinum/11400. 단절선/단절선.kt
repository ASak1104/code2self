import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt() = run {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val k = readInt()
    val graph = Graph(n)

    repeat(k) {
        graph.addEdge(readInt(), readInt())
    }

    val res = graph.findSpfEdges()

    with(System.out.bufferedWriter()) {
        append("${res.size}\n")
        res.forEach { append("${it.u} ${it.v}\n") }
        flush()
        close()
    }
}

class Graph(val n: Int) {
    val edges = Array(n + 1) { ArrayList<Int>() }
    val spfEdges = ArrayList<Point>()
    val ids = IntArray(n + 1)
    var id = 0

    fun addEdge(u: Int, v: Int) {
        edges[u] += v
        edges[v] += u
    }

    fun findSpfEdges(): ArrayList<Point> {
        for (u in 1..n) {
            if (ids[u] == 0) travel(u, 0)
        }

        spfEdges.sortWith(compareBy(Point::u, Point::v))

        return spfEdges
    }

    fun travel(u: Int, p: Int): Int {
        ids[u] = ++id

        var minId = id

        for (v in edges[u]) {
            if (v == p) continue

            if (ids[v] > 0) {
                minId = minOf(minId, ids[v])
                continue
            }

            val next = travel(v, u)

            if (next > ids[u]) {
                spfEdges += Point(u, v)
            }

            minId = minOf(minId, next)
        }

        return minId
    }
}

class Point(var u: Int, var v: Int) {
    init {
        if (u > v) {
            val temp = u

            u = v
            v = temp
        }
    }
}