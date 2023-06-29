import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val e = readInt()
    val edges = Array(n + 1) { ArrayList<Int>() }

    repeat(e) {
        val u = readInt()
        val v = readInt()

        edges[u] += v
        edges[v] += u
    }

    val spfPoints = sortedSetOf<Int>()
    val ids = IntArray(n + 1)
    var id = 0

    fun travel(u: Int, p: Int): Int {
        ids[u] = ++id

        if (edges[u].isEmpty()) return id

        var minId = id
        var travels = 0

        for (v in edges[u]) {
            if (ids[v] == 0) {
                minId = minOf(minId, travel(v, u))
                travels++
            }
            else if (v != p) {
                minId = minOf(minId, ids[v])
            }
        }

        if (minId >= ids[p]) spfPoints += p

        if (u == p && travels == 1) spfPoints -= u

        return minId
    }

    for (u in 1..n) {
        if (ids[u] == 0) travel(u, u)
    }

    with(System.out.bufferedWriter()) {
        append("${spfPoints.size}\n")
        spfPoints.joinTo(this, " ")
        flush()
        close()
    }
}