import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val n = readInt()
    val edges = Array(n) { ArrayList<Int>() }

    repeat(n - 1) {
        val u = readInt() - 1
        val v = readInt() - 1

        edges[u].add(v)
        edges[v].add(u)
    }

    val visit = BooleanArray(n)
    val parent = IntArray(n)

    fun travel(u: Int) {
        visit[u] = true

        for (v in edges[u]) {
            if (!visit[v]) {
                parent[v] = u + 1
                travel(v)
            }
        }
    }

    travel(0)

    with(System.out.bufferedWriter()) {
        for (i in 1 until n) {
            append("${parent[i]}\n")
        }
        flush()
        close()
    }
}