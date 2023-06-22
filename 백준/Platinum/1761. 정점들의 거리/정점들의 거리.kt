import java.io.StreamTokenizer
import kotlin.math.log2

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val edges = Array(n + 1) { ArrayList<Edge>() }

    repeat(n - 1) {
        val u = readInt()
        val v = readInt()
        val w = readInt()

        edges[u].add(Edge(v, w))
        edges[v].add(Edge(u, w))
    }

    val graph = Graph(n)

    graph.buildTree(edges)

    val bw = System.out.bufferedWriter()

    repeat(readInt()) {
        val u = readInt()
        val v = readInt()

        bw.append("${graph.query(u, v)}\n")
    }
    bw.flush()
    bw.close()
}

class Graph(n: Int) {
    val height = log2(n.toDouble()).toInt()
    val edges = IntArray(n + 1)
    val parents = Array(n + 1) { IntArray(height + 1) }
    val depths = IntArray(n + 1)
    var root = 1

    fun buildTree(undirectedEdge: Array<ArrayList<Edge>>) {
        fun dfs(u: Int, parent: Int) {
            depths[u] = depths[parent] + 1
            parents[u][0] = parent

            for (d in 1..height) {
                val p = parents[u][d - 1]

                parents[u][d] = parents[p][d - 1]
            }

            for (edge in undirectedEdge[u]) {
                if (edge.v != parent) {
                    edges[edge.v] = edge.w

                    dfs(edge.v, u)
                }
            }
        }

        depths[root] = -1
        dfs(root, root)
    }

    fun query(qu: Int, qv: Int): Int {
        if (depths[qu] < depths[qv]) return query(qv, qu)

        var u = qu

        for (h in height downTo 0) {
            if (depths[u] == depths[qv]) break

            val pu = parents[u][h]

            if (depths[pu] >= depths[qv]) u = pu
        }

        if (u == qv) return distance(qu, u, 0)

        var v = qv
        var lca = u

        for (h in height downTo 0) {
            val pu = parents[u][h]
            val pv = parents[v][h]

            when (pu) {
                pv -> lca = pu
                else -> {
                    u = pu
                    v = pv
                }
            }
        }

        return distance(qu, lca, 0) + distance(qv, lca, 0)
    }

    tailrec fun distance(u: Int, v: Int, w: Int): Int {
        if (u == v) return w

        return distance(parents[u].first(), v, w + edges[u])
    }
}

data class Edge(val v: Int, val w: Int)