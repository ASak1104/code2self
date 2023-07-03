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

    with(System.out.bufferedWriter()) {
        repeat(readInt()) {
            append(network.query(readInt(), readInt()))
        }

        flush()
        close()
    }
}

class Network(val n: Int) {
    val edges = Array(n + 1) { ArrayList<Edge>() }
    val depths = IntArray(n + 1)
    val height = log2(n.toDouble()).toInt()
    val ancestors = Array(height + 1) { IntArray(n + 1) }
    val mins = Array(height + 1) { Array(n + 1) { MAX } }
    val maxs = Array(height + 1) { Array(n + 1) { MIN } }

    fun addEdge(u: Int, v: Int, w: Int) {
        edges[u] += Edge(v, w)
        edges[v] += Edge(u, w)
    }

    fun build() {
        depths[0] = -1

        travel(1, 0)

        for (i in 1..height) {
            for (u in 2..n) {
                if (ancestors[i - 1][u] == 0) continue

                val au = ancestors[i - 1][u]

                ancestors[i][u] = ancestors[i - 1][au]
                mins[i][u] = minOf(mins[i - 1][u], mins[i - 1][au])
                maxs[i][u] = maxOf(maxs[i - 1][u], maxs[i - 1][au])
            }
        }
    }

    fun travel(u: Int, p: Int) {
        depths[u] = depths[p] + 1
        ancestors[0][u] = p

        for (edge in edges[u]) {
            if (edge.v == p) {
                mins[0][u] = edge.w
                maxs[0][u] = edge.w
                continue
            }

            travel(edge.v, u)
        }
    }

    fun query(qu: Int, qv: Int): String {
        if (depths[qu] < depths[qv]) return query(qv, qu)

        var min = MAX
        var max = MIN
        var u = qu
        var v = qv

        for (i in height downTo 0) {
            if (depths[u] == depths[v]) break

            if (depths[ancestors[i][u]] >= depths[v]) {
                min = minOf(min, mins[i][u])
                max = maxOf(max, maxs[i][u])
                u = ancestors[i][u]
            }
        }

        if (u == v) return "$min $max\n"

        for (i in height downTo 0) {
            if (ancestors[i][u] == ancestors[i][v]) continue

            min = minOf(min, mins[i][u], mins[i][v])
            max = maxOf(max, maxs[i][u], maxs[i][v])
            u = ancestors[i][u]
            v = ancestors[i][v]
        }

        min = minOf(min, mins[0][u], mins[0][v])
        max = maxOf(max, maxs[0][u], maxs[0][v])

        return "$min $max\n"
    }
}

data class Edge(val v: Int, val w: Int)