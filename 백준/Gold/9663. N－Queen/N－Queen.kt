fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    close()

    val nq = NQueen(n)

    with(System.out.bufferedWriter()) {
        append("${nq.res}").flush()
        close()
    }
}

class NQueen(val n: Int) {
    val board = Array(n) { IntArray(n) }
    val colW = intArrayOf(1, -1)
    var res = 0

    init {
        val mid = (n ushr 1)

        for (c in 0 until mid) {
            solve(0, c)
        }

        res *= 2

        if (n % 2 == 1) solve(0, mid)
    }

    fun solve(sr: Int, sc: Int) {
        if (sr == n - 1) {
            res++
            return
        }

        mark(sr, sc) { i: Int, j: Int -> board[i][j]++ }

        val r = sr + 1

        for (c in board.indices) {
            if (board[r][c] == 0) {
                solve(r, c)
            }
        }

        mark(sr, sc) { i: Int, j: Int -> board[i][j]-- }
    }

    inline fun mark(sr: Int, sc: Int, op: (Int, Int) -> Unit) {
        repeat(n) { op(it, sc) }

        for (w in colW) {
            var r = sr + 1
            var c = sc + w

            while (r in board.indices && c in board.indices) {
                op(r, c)
                r++
                c += w
            }
        }
    }
}