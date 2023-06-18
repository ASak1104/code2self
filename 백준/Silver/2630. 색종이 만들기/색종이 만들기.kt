import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val paper = Array(n) {
        StringTokenizer(readLine()).run {
            IntArray(n) { nextToken().toInt() }
        }
    }

    val res = IntArray(2)

    fun travel(sr: Int, sc: Int, k: Int) {
        val v = paper[sr][sc]

        for (r in sr until sr + k) {
            for (c in sc until sc + k) {
                if (paper[r][c] == v) continue

                val nk = (k ushr 1)

                for (nr in sr until sr + k step nk) {
                    for (nc in sc until sc + k step nk) {
                        travel(nr, nc, nk)
                    }
                }
                return
            }
        }
        res[v]++
    }

    travel(0, 0, n)

    with(System.out.bufferedWriter()) {
        res.forEach { append("$it\n") }
        flush()
        close()
    }
}