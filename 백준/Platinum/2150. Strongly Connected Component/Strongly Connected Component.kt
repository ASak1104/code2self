import java.io.StreamTokenizer
import java.util.*
import kotlin.collections.ArrayDeque

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val V = readInt()
    val E = readInt()
    val edges = Array(V + 1) { ArrayList<Int>() }

    repeat(E) {
        val u = readInt()
        val v = readInt()

        edges[u] += v
    }

    val res = PriorityQueue<ArrayList<Int>>(compareBy { it[0] })
    val stack = ArrayDeque<Int>(V)
    val finish = BooleanArray(V + 1)
    val parent = IntArray(V + 1)
    var id = 0

    fun findSCC(u: Int): Int {
        parent[u] = ++id
        stack.addLast(u)

        var p = id

        for (v in edges[u]) {
            if (parent[v] == 0) {
                p = minOf(p, findSCC(v))
            }
            else if (!finish[v]) {
                p = minOf(p, parent[v])
            }
        }

        if (p == parent[u]) {
            val scc = ArrayList<Int>()

            do {
                val pop = stack.removeLast()

                finish[pop] = true
                scc += pop
            } while (pop != u)

            scc.sort()
            res.add(scc)
        }

        return p
    }

    for (u in 1..V) {
        if (parent[u] == 0) findSCC(u)
    }

    with(System.out.bufferedWriter()) {
        append("${res.size}\n")
        while (res.isNotEmpty()) {
            res.poll().forEach { append("$it ") }
            append("-1\n")
        }
        flush()
        close()
    }
}