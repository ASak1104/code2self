import java.io.StreamTokenizer

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()
    val gates = DisjointSet(n)

    var count = m

    for (i in 0 until m) {
        val airplane = readInt()

        if (!gates.union(airplane)) {
            count = i
            break
        }
    }

    with(System.out.bufferedWriter()) {
        append("$count")
        flush()
        close()
    }
}

class DisjointSet(val n: Int) {
    val parents = IntArray(n + 1) { it }

    tailrec fun find(u: Int): Int {
        return if (u == parents[u])
            u
        else
            find(parents[u])
    }

    fun union(u: Int): Boolean {
        val ru = find(u)

        if (ru == 0) return false

        val rv = find(ru - 1)

        parents[u] = rv
        parents[ru] = rv

        return true
    }
}