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
        val w = readInt()

        edges[u].add(Edge(v, w))
        edges[v].add(Edge(u, w))
    }

    val graph = Graph(n)

    graph.buildTree(edges)

    val bw = System.out.bufferedWriter()

    repeat(readInt()) {
        val u = readInt() - 1
        val v = readInt() - 1

        bw.append("${graph.query(u, v)}\n")
    }
    bw.flush()
    bw.close()
}

class Graph(val n: Int) {
    val parents = Array(n) { Edge(0, 0) }
    val dist = IntArray(n)
    var root = 0

    fun buildTree(edges: Array<ArrayList<Edge>>) {
        val visit = BooleanArray(n)

        fun travel(u: Int) {
            visit[u] = true

            for (edge in edges[u]) {
                if (!visit[edge.v]) {
                    parents[edge.v].v = u
                    parents[edge.v].w = edge.w

                    travel(edge.v)
                }
            }
        }
        
        travel(root)
    }

    fun query(u: Int, v: Int): Int {
        dist.fill(-1)

        climbTree(u, 0)

        return climbTree(v, 0)
    }

    fun climbTree(u: Int, d: Int): Int {
        if (dist[u] > -1) return dist[u] + d

        dist[u] = d

        return climbTree(parents[u].v, d + parents[u].w)
    }
}

data class Edge(var v: Int, var w: Int)