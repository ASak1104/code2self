import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val edges = Array(n) { ArrayList<Edge>() }

    repeat(n - 1) {
        val u = readInt() - 1
        val v = readInt() - 1
        val c = readInt()

        edges[u].add(Edge(v, c))
        edges[v].add(Edge(u, c))
    }

    val visit = BooleanArray(n)
    var maxDist = 0
    var maxIndex = 0

    fun travel(u: Int, d: Int) {
        visit[u] = true

        if (maxDist < d) {
            maxDist = d
            maxIndex = u
        }

        for ((v, c) in edges[u]) {
            if (!visit[v]) travel(v, d + c)
        }
    }

    travel(0, 0)

    visit.fill(false)

    travel(maxIndex, 0)

    with(System.out.bufferedWriter()) {
        append("$maxDist").flush()
        close()
    }
}

data class Edge(val v: Int, val c: Int)