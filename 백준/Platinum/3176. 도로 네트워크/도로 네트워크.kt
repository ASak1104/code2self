import java.io.StreamTokenizer
import kotlin.math.log2

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val network = Network(n)

    repeat(n - 1) {
        network.addEdge(readInt(), readInt(), readInt())
    }

    network.build()

    val bw = System.out.bufferedWriter()

    repeat(readInt()) {
        val (min, max) = network.query(readInt(), readInt())

        bw.append("$min $max\n")
    }

    bw.flush()
    bw.close()
}

class Network(val n: Int) {
    val edges = Array(n + 1) { ArrayList<Pair<Int, Int>>() }
    val depths = IntArray(n + 1)
    val height = log2(n.toDouble()).toInt()
    val ancestors = Array(n + 1) { IntArray(height + 1) }
    val mins = Array(n + 1) { Array(height + 1) { Int.MAX_VALUE } }
    val maxs = Array(n + 1) { Array(height + 1) { Int.MIN_VALUE } }

    fun addEdge(u: Int, v: Int, w: Int) {
        edges[u] += v to w
        edges[v] += u to w
    }

    fun build() {
        val nodes = ArrayList<Int>(n)

        fun dfs(u: Int, p: Int) {
            nodes.add(u)
            depths[u] = depths[p] + 1
            ancestors[u][0] = p

            for (i in 1..height) {
                val au = ancestors[u][i - 1]

                ancestors[u][i] = ancestors[au][i - 1]
            }

            for ((v, w) in edges[u]) {
                when {
                    v != p -> dfs(v, u)
                    else -> {
                        mins[u][0] = w
                        maxs[u][0] = w
                    }
                }
            }
        }

        depths[0] = -1
        dfs(1, 0)

        for (u in nodes) {
            for (i in 1..height) {
                if (ancestors[u][i] == 0) break

                val au = ancestors[u][i - 1]

                mins[u][i] = minOf(mins[u][i - 1], mins[au][i - 1])
                maxs[u][i] = maxOf(maxs[u][i - 1], maxs[au][i - 1])
            }
        }
    }

    fun query(qu: Int, qv: Int): Pair<Int, Int> {
        if (depths[qu] < depths[qv]) return query(qv, qu)

        var u = qu
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE

        while (depths[u] != depths[qv]) {
            var i = height

            while (i > 0 && depths[ancestors[u][i]] < depths[qv]) i--

            min = minOf(min, mins[u][i])
            max = maxOf(max, maxs[u][i])
            u = ancestors[u][i]
        }

        if (u == qv) return min to max

        var v = qv

        while (u != v) {
            var i = height

            while (i > 0 && ancestors[u][i] == ancestors[v][i]) i--

            min = minOf(min, mins[u][i], mins[v][i])
            max = maxOf(max, maxs[u][i], maxs[v][i])
            u = ancestors[u][i]
            v = ancestors[v][i]
        }

        return min to max
    }
}