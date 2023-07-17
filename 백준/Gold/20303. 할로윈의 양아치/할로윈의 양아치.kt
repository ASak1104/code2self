import java.io.StreamTokenizer

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()
    val k = readInt()

    val candies = IntArray(n) { readInt() }
    val edges = Array(n) { ArrayList<Int>() }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1

        edges[u] += v
        edges[v] += u
    }

    val visit = BooleanArray(n)

    fun travel(u: Int, item: Item) {
        visit[u] = true

        item.c++
        item.w += candies[u]

        for (v in edges[u]) {
            if (!visit[v]) {
                travel(v, item)
            }
        }
    }

    val merged = ArrayList<Item>()

    for (u in candies.indices) {
        if (!visit[u]) {
            val item = Item(0, 0)

            travel(u, item)
            merged += item
        }
    }

    val dp = Array(merged.size + 1) { IntArray(k + 1) }

    for (i in 1 until dp.size) {
        val item = merged[i - 1]

        for (j in 1..k) {
            dp[i][j] = if (j <= item.c)
                dp[i - 1][j]
            else
                maxOf(dp[i - 1][j], dp[i - 1][j - item.c] + item.w)
        }
    }

    with(System.out.bufferedWriter()) {
        append("${dp[dp.lastIndex][k]}")
        flush()
        close()
    }
}

class Item(var c: Int, var w: Int)