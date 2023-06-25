import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }
    val n = readInt()
    val edges = Array(n) { arrayListOf<Int>() }
    var root = 0

    repeat(n) { v ->
        when (val u = readInt()) {
            -1 -> root = v
            else -> edges[u].add(v)
        }
    }

    val exceptNode = readInt()
    var leaves = 0

    fun travel(u: Int) {
        if (u == exceptNode) return

        var children = 0

        for (v in edges[u]) {
            if (v != exceptNode) {
                travel(v)
                children++
            }
        }

        if (children == 0) leaves++
    }

    travel(root)

    with(System.out.bufferedWriter()) {
        append("$leaves")
        flush()
        close()
    }
}