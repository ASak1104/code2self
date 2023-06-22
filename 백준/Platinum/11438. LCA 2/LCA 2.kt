import java.io.StreamTokenizer
import kotlin.math.log2

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val edges = Array(n + 1) { ArrayList<Int>() }

    repeat(n - 1) {
        val u = readInt()
        val v = readInt()

        edges[u].add(v)
        edges[v].add(u)
    }

    val tree = Tree(n)

    tree.build(edges)

    val bw = System.out.bufferedWriter()

    repeat(readInt()) {
        val u = readInt()
        val v = readInt()

        bw.append("${tree.query(u, v)}\n")
    }

    bw.flush()
    bw.close()
}

class Tree(n: Int) {
    val height = log2(n.toDouble()).toInt()
    val parents = Array(n + 1) { IntArray(height + 1) }
    val depths = IntArray(n + 1)

    fun build(undirectedEdge: Array<ArrayList<Int>>) {
        fun dfs(u: Int, p: Int) {
            depths[u] = depths[p] + 1
            parents[u][0] = p

            for (d in 1..height) {
                val ancestor = parents[u][d - 1]
                parents[u][d] = parents[ancestor][d - 1]
            }

            for (v in undirectedEdge[u]) {
                if (v != p) dfs(v, u)
            }
        }

        depths[0] = -1
        dfs(1, 0)
    }

    fun query(qu: Int, qv: Int): Int {
        if (depths[qu] < depths[qv]) return query(qv, qu)

        var u = qu

        for (h in height downTo 0) {
            if (depths[u] == depths[qv]) break

            val pu = parents[u][h]

            if (depths[pu] >= depths[qv]) u = pu
        }

        if (u == qv) return u

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

        return lca
    }
}
