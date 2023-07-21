import java.io.StreamTokenizer

val INF = 1e8.toInt()
val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt(): Int = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()

    val dists = Array(n) { IntArray(n) { INF } }

    repeat(n) { dists[it][it] = 0 }

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        if (dists[u][v] > w) {
            dists[u][v] = w
            dists[v][u] = w
        }

    }

    val v1 = readInt() - 1
    val v2 = readInt() - 1

    for (k in dists.indices) {
        for (u in dists.indices) {
            for (v in dists.indices) {
                val dist = dists[u][k] + dists[k][v]

                if (dists[u][v] > dist) {
                    dists[u][v] = dist
                }
            }
        }
    }

    val res = minOf(
        dists[0][v1] + dists[v1][v2] + dists[v2][n - 1],
        dists[0][v2] + dists[v2][v1] + dists[v1][n - 1]
    )

    with(System.out.bufferedWriter()) {
        append("${if (res < INF) res else -1}")
        flush()
        close()
    }
}