import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val edges = ArrayList<Edge>(m)

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        edges += Edge(u, v, w)
    }

    edges.sortBy(Edge::w)

    val set = DisjointSet(n)
    var res = 0

    with(set) {
        for ((u, v, w) in edges) {
            if (network == 2) break

            if (find(u) == find(v)) continue

            union(u, v)

            res += w
        }
    }

    with(System.out.bufferedWriter()) {
        append(res.toString())
        flush()
        close()
    }
}

class DisjointSet(n: Int) {
    val parents = IntArray(n) { it }
    val heights = IntArray(n)
    var network = n

    fun find(u: Int): Int {
        var root = u

        while (root != parents[root]) {
            root = parents[root]
        }

        return root
    }

    fun union(u: Int, v: Int) {
        val ru = find(u)
        val rv = find(v)

        if (heights[ru] < heights[rv]) return union(rv, ru)

        parents[rv] = ru
        network--

        if (heights[ru] == heights[rv]) heights[ru]++
    }
}

data class Edge(val u: Int, val v: Int, val w: Int)