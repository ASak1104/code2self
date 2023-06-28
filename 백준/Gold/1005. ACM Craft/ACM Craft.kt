import java.io.StreamTokenizer

lateinit var dp: IntArray
lateinit var costs: IntArray
lateinit var revEdges: Array<ArrayList<Int>>

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    val readInt = {
        nextToken()
        nval.toInt()
    }

    repeat(readInt()) {
        val n = readInt()
        val k = readInt()

        dp = IntArray(n) { -1 }
        costs = IntArray(n) { readInt() }
        revEdges = Array(n) { ArrayList() }

        repeat(k) {
            val u = readInt() - 1
            val v = readInt() - 1

            revEdges[v] += u
        }

        bw.append("${acmCraft(readInt() - 1)}\n")
    }

    bw.flush()
    bw.close()
}

fun acmCraft(v: Int): Int {
    if (dp[v] >= 0) return dp[v]

    var prev = 0

    for (u in revEdges[v]) {
        prev = maxOf(prev, acmCraft(u))
    }

    dp[v] = prev + costs[v]

    return dp[v]
}
