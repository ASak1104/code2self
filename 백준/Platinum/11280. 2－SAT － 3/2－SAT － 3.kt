import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val scc = SCC(n)

    repeat(m) {
        var u = readInt()
        var v = readInt()

        if (u < 0) u = n - u

        if (v < 0) v = n - v

        val invU = if (u > n) u - n else u + n
        val invV = if (v > n) v - n else v + n

        scc.addEdge(invU, v)
        scc.addEdge(invV, u)
    }

    scc.build()

    with(System.out.bufferedWriter()) {
        append(if (scc.clause) "1" else "0")
        flush()
        close()
    }
}

class SCC(val n: Int) {
    var clause = true
    val edges = Array(n * 2 + 1) { ArrayList<Int>() }
    val stack = ArrayDeque<Int>()
    val finish = BooleanArray(n * 2 + 1)
    val scc = IntArray(n * 2 + 1)
    var cc = 0
    val ids = IntArray(n * 2 + 1)
    var id = 0

    fun addEdge(u: Int, v: Int) {
        edges[u] += v
    }

    fun build() {
        for (u in 1..n * 2) {
            if (ids[u] == 0) findSCC(u)
        }

        for (u in 1..n) {
            if (!clause) return

            if (scc[u] == scc[u + n]) {
                clause = false
            }
        }
    }

    fun findSCC(u: Int): Int {
        ids[u] = ++id
        stack.addLast(u)

        var minId = id

        for (v in edges[u]) {
            if (ids[v] == 0) {
                minId = minOf(minId, findSCC(v))
            }
            else if (!finish[v]) {
                minId = minOf(minId, ids[v])
            }
        }

        if (minId == ids[u]) {
            cc++

            do {
                val pop = stack.removeLast()

                finish[pop] = true
                scc[pop] = cc
            } while (pop != u)
        }

        return minId
    }
}