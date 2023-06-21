const val INF = 1e9.toInt()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val nq = NQueen(n)

    with(System.out.bufferedWriter()) {
        append("${nq.res}")
        flush()
        close()
    }
}

class NQueen(val n: Int) {
    val board = Array(n) { IntArray(n) }
    val rowW = intArrayOf(1, 1, -1, -1)
    val colW = intArrayOf(1, -1, 1, -1)
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

        mark(sr, sc)

        val r = sr + 1

        for (c in board.indices) {
            if (board[r][c] == 0) {
                solve(r, c)
            }
        }

        demark(sr, sc)
    }

    fun mark(sr: Int, sc: Int) {
        repeat(n) { r -> board[r][sc]++ }
        repeat(n) { c -> board[sr][c]++ }

        board[sr][sc]--

        for (i in 0..3) {
            var r = sr + rowW[i]
            var c = sc + colW[i]

            while (r in board.indices && c in board.indices) {
                board[r][c]++

                r += rowW[i]
                c += colW[i]
            }
        }
    }

    fun demark(sr: Int, sc: Int) {
        repeat(n) { r -> board[r][sc]-- }
        repeat(n) { c -> board[sr][c]-- }

        board[sr][sc]++

        for (i in 0..3) {
            var r = sr + rowW[i]
            var c = sc + colW[i]

            while (r in board.indices && c in board.indices) {
                board[r][c]--

                r += rowW[i]
                c += colW[i]
            }
        }
    }
}