import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val board = Array(9) { IntArray(9) { readInt() } }
    val sudoku = Sudoku(board)

    sudoku.solve(0, 0)

    with(System.out.bufferedWriter()) {
        board.forEach {
            it.joinTo(this, " ")
            newLine()
        }

        flush()
        close()
    }
}

class Sudoku(val board: Array<IntArray>) {

    fun solve(sr: Int, sc: Int): Boolean {
        for (c in sc until 9) {
            if (board[sr][c] > 0) continue

            if (!nextStep(sr, c)) return false
        }

        for (r in sr + 1 until 9) {
            for (c in board.indices) {
                if (board[r][c] > 0) continue

                if (!nextStep(r, c)) return false
            }
        }

        return true
    }

    fun nextStep(r: Int, c: Int): Boolean {
        board[r][c] = getMin(r, c, 1)

        if (board[r][c] == 0) return false

        while (!solve(r, c + 1)) {
            board[r][c] = getMin(r, c, board[r][c] + 1)

            if (board[r][c] == 0) return false
        }

        return true
    }

    fun getMin(sr: Int, sc: Int, start: Int): Int {
        val set = (start..9).toMutableSet()

        repeat(9) { set -= board[it][sc] }

        repeat(9) { set -= board[sr][it] }

        repeat(3) { ir ->
            val r = (sr / 3) * 3
            val c = (sc / 3) * 3

            repeat(3) { ic -> set -= board[r + ir][c + ic] }
        }

        return set.minOrNull() ?: 0
    }
}