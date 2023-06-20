import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val ramens = StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } }
    close()

    var res = 0

    fun buyWith(from: Int, to: Int, price: Int) {
        var min = 10000

        for (k in from..to) {
            if (min > ramens[k]) min = ramens[k]
        }

        if (min > 0) {
            res += price * min

            for (k in from..to) {
                ramens[k] -= min
            }
        }
    }

    for (i in 0 until n) {
        if (i < n - 2) {
            if (ramens[i + 1] > ramens[i + 2]) {
                ramens[i + 1] -= ramens[i + 2]

                buyWith(i, i + 1, 5)

                ramens[i + 1] += ramens[i + 2]
            }
            buyWith(i, i + 2, 7)
        }

        if (i < n - 1) {
            buyWith(i, i + 1, 5)
        }

        res += 3 * (ramens[i])
    }

    with(System.out.bufferedWriter()) {
        append("$res").flush()
        close()
    }
}