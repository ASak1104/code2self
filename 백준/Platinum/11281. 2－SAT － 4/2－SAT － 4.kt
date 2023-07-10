import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val graph = Graph(n)

    repeat(m) {
        graph.addEdge(readInt(), readInt())
    }

    graph.build()

    with(System.out.bufferedWriter()) {
        if (graph.clause) {
            append("1\n")

            graph.getFunction().forEach {
                append("$it ")
            }
        } else {
            append("0")
        }
        
        flush()
        close()
    }
}

class Graph(val n: Int) {
    val edges = Array(n * 2 + 1) { ArrayList<Int>() }
    val stack = ArrayDeque<Int>()
    val finish = BooleanArray(n * 2 + 1)
    val ids = IntArray(n * 2 + 1)
    val group = IntArray(n * 2 + 1)
    var clause = true
    var id = 0
    var gid = 0

    fun addEdge(qu: Int, qv: Int) {
        val (u, invU) = getPair(qu)
        val (v, invV) = getPair(qv)

        edges[invU] += v
        edges[invV] += u
    }

    fun build() {
        for (u in 1..n * 2) {
            if (ids[u] == 0) findSCC(u)
        }

        for (u in 1..n) {
            if (group[u] == group[u + n]) {
                clause = false
                return
            }
        }
    }

    fun getFunction(): IntArray {
        return IntArray(n) {
            if (group[it + 1] < group[it + n + 1]) 1 else 0
        }
    }

    fun getPair(x: Int): Pair<Int, Int> {
        return if (x > 0)
            x to x + n
        else
            n - x to -x
    }

    fun findSCC(u: Int): Int {
        stack += u
        ids[u] = ++id

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
            gid++

            do {
                val pop = stack.removeLast()

                group[pop] = gid
                finish[pop] = true
            } while (pop != u)
        }

        return minId
    }
}