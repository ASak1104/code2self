import java.io.StreamTokenizer
import kotlin.math.log2

const val MIN = 1
const val MAX = 1_000_000

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
    val ancestors = Array(height + 1) { IntArray(n + 1) }
    val mins = Array(height + 1) { Array(n + 1) { MAX } }
    val maxs = Array(height + 1) { Array(n + 1) { MIN } }

    fun addEdge(u: Int, v: Int, w: Int) {
        edges[u] += v to w
        edges[v] += u to w
    }

    fun build() {
        val nodes = ArrayList<Int>(n)

        fun dfs(u: Int, p: Int) {
            nodes.add(u)
            depths[u] = depths[p] + 1
            ancestors[0][u] = p

            for (i in 1..height) {
                val au = ancestors[i - 1][u]

                ancestors[i][u] = ancestors[i - 1][au]
            }

            for ((v, w) in edges[u]) {
                when {
                    v != p -> dfs(v, u)
                    else -> {
                        mins[0][u] = w
                        maxs[0][u] = w
                    }
                }
            }
        }

        depths[0] = -1
        dfs(1, 0)

        for (u in nodes) {
            for (i in 1..height) {
                if (ancestors[i][u] == 0) break

                val au = ancestors[i - 1][u]

                mins[i][u] = minOf(mins[i - 1][u], mins[i - 1][au])
                maxs[i][u] = maxOf(maxs[i - 1][u], maxs[i - 1][au])
            }
        }
    }

    fun query(qu: Int, qv: Int): Pair<Int, Int> {
        if (depths[qu] < depths[qv]) return query(qv, qu)

        var u = qu
        var min = MAX
        var max = MIN

        while (depths[u] != depths[qv]) {
            var i = height

            while (i > 0 && depths[ancestors[i][u]] < depths[qv]) i--

            min = minOf(min, mins[i][u])
            max = maxOf(max, maxs[i][u])
            u = ancestors[i][u]
        }

        if (u == qv) return min to max

        var v = qv

        while (u != v) {
            var i = height

            while (i > 0 && ancestors[i][u] == ancestors[i][v]) i--

            min = minOf(min, mins[i][u], mins[i][v])
            max = maxOf(max, maxs[i][u], maxs[i][v])
            u = ancestors[i][u]
            v = ancestors[i][v]
        }

        return min to max
    }
}