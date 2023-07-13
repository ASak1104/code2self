import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()
    val graph = Graph(n)

    var res = 0

    for (i in 1..m) {
        val u = readInt()
        val v = readInt()

        graph.addEdge(u, v)

        if (graph.hasCycle) {
            res = i
            break
        }
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}

class Graph(val n: Int) {
    val parents = IntArray(n) { it }
    val heights = IntArray(n)
    var hasCycle = false

    fun addEdge(u: Int, v: Int) {
        val ru = find(u)
        val rv = find(v)

        if (ru == rv) {
            hasCycle = true
            return
        }

        if (heights[ru] == heights[rv]) {
            heights[ru]++
        }

        if (heights[ru] > heights[rv]) {
            parents[rv] = ru
        } else {
            parents[ru] = rv
        }
    }

    tailrec fun find(u: Int): Int {
        return if (u == parents[u])
            u
        else
            find(parents[u])
    }
}