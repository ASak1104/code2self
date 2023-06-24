import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayDeque

const val MAX_VALUE = 1e9.toInt()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val bw = System.out.bufferedWriter()
    var n = readInt()
    var m = readInt()

    while (n > 1) {
        val graph = Graph(n, readInt(), readInt())

        repeat(m) { graph.edges[readInt()][readInt()] = readInt() }

        graph.removeShortestPaths()

        bw.append("${graph.getShortestDistance()}\n")

        n = readInt()
        m = readInt()
    }

    bw.flush()
    bw.close()
}

class Graph(val n: Int, val s: Int, val e: Int) {
    val edges = Array(n) { IntArray(n) }

    fun removeShortestPaths() {
        val revEdges = findShortestPaths()

        val visit = BooleanArray(n)
        val queue = ArrayDeque<Int>()

        visit[e] = true
        queue.addLast(e)

        while (queue.isNotEmpty()) {
            val v = queue.removeFirst()

            for (u in revEdges[v]) {
                edges[u][v] = 0

                if (!visit[u]) {
                    visit[u] = true
                    queue.addLast(u)
                }
            }
        }
    }

    fun findShortestPaths(): Array<ArrayList<Int>> {
        val pq = PriorityQueue(compareBy(Pair<Int, Int>::second))
        val revEdges = Array(n) { arrayListOf<Int>() }
        val dists = IntArray(n) { MAX_VALUE }

        pq.add(s to 0)
        dists[s] = 0

        while (pq.isNotEmpty()) {
            val (u, d) = pq.poll()

            if (d > dists[e]) break

            for (v in edges.indices) {
                if (edges[u][v] == 0) continue

                val dw = d + edges[u][v]

                if (dists[v] < dw) continue

                if (dists[v] != dw) {
                    dists[v] = dw
                    pq.add(v to dw)
                    revEdges[v].clear()
                }

                revEdges[v] += u
            }
        }

        return revEdges
    }

    fun getShortestDistance(): Int {
        val dists = IntArray(n) { MAX_VALUE }
        val pq = PriorityQueue(compareBy(dists::get))

        dists[s] = 0
        pq.add(s)

        while (pq.isNotEmpty()) {
            val u = pq.poll()

            if (u == e) break

            for (v in edges.indices) {
                if (edges[u][v] == 0) continue

                val dw = dists[u] + edges[u][v]

                if (dists[v] > dw) {
                    dists[v] = dw
                    pq.add(v)
                }
            }
        }

        return if (dists[e] < MAX_VALUE)
            dists[e]
        else
            -1
    }
}