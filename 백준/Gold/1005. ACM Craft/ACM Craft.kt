import java.io.StreamTokenizer

lateinit var costs: IntArray
lateinit var edges: Array<ArrayList<Int>>
lateinit var indegrees: IntArray
var n = 0
var k = 0

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    val readInt = {
        nextToken()
        nval.toInt()
    }

    repeat(readInt()) {
        n = readInt()
        k = readInt()

        costs = IntArray(n) { readInt() }
        edges = Array(n) { ArrayList() }
        indegrees = IntArray(n)

        repeat(k) {
            val u = readInt() - 1
            val v = readInt() - 1

            edges[u] += v
            indegrees[v]++
        }

        bw.append("${acmCraft(readInt() - 1)}\n")
    }

    bw.flush()
    bw.close()
}

fun acmCraft(end: Int): Int {
    val dists = IntArray(n)
    val queue = ArrayDeque<Int>()

    for (u in 0 until n) {
        if (indegrees[u] == 0) {
            indegrees[u]--
            dists[u] = costs[u]
            queue.add(u)
        }
    }

    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()

        for (v in edges[u]) {
            dists[v] = maxOf(dists[v], dists[u] + costs[v])

            if (--indegrees[v] == 0) {
                queue.addLast(v)
            }
        }
    }

    return dists[end]
}