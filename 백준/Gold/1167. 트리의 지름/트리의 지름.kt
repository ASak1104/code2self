import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val edges = Array(n) { arrayListOf<Node>() }

    repeat(n) {
        val st = StringTokenizer(readLine())
        val u = st.nextToken().toInt() - 1
        var v = st.nextToken().toInt() - 1

        while (v >= 0) {
            edges[u].add(Node(v, st.nextToken().toInt()))
            v = st.nextToken().toInt() - 1
        }
    }
    close()

    val visit = BooleanArray(n)
    var max = 0
    var maxIndex = 0

    fun travel(u: Int, d: Int) {
        visit[u] = true

        if (d > max) {
            max = d
            maxIndex = u
        }

        for (v in edges[u]) {
            if (!visit[v.v]) {
                travel(v.v, d + v.c)
            }
        }
    }

    travel(0, 0)

    visit.fill(false)

    travel(maxIndex, 0)

    with(System.out.bufferedWriter()) {
        append("$max").flush()
        close()
    }
}

class Node(val v: Int, val c: Int)