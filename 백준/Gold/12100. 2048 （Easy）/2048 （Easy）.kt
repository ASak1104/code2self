import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max

val moves = arrayOf(Move(0, 1), Move(0, -1), Move(1, 0), Move(-1, 0))

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val board = List(n) {
        StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } }
    }
    close()

    var localMax = board.maxOf { it.max() }
    val queue = ArrayDeque<Game>(4000)
    queue.addLast(Game(0, board))

    while (queue.isNotEmpty()) {
        val game = queue.removeFirst()

        if (game.round == 5) {
            localMax = max(localMax, game.max())
            continue
        }
        for (move in moves) {
            val newGame = game.next(move)
            var i = 0
            val diff = game.board.any { !it.contentEquals(newGame.board[i++]) }

            if (diff) queue.addLast(newGame)
        }
    }
    print(localMax)
}

class Game(val round: Int, val board: List<IntArray>) {

    fun next(move: Move): Game {
        val nextBoard = board.map { it.copyOf() }
        val nextGame = Game(round + 1, nextBoard)

        nextGame.attachAll(move)
        when (move.row) {
            0 -> nextGame.calcHorizontal(move)
            else -> nextGame.calcVertical(move)
        }
        nextGame.attachAll(move)

        return nextGame
    }

    fun calcHorizontal(move: Move) {
        val range: IntProgression
        val offset: Int

        if (move.col == 1) {
            range = board.lastIndex downTo 1
            offset = -1
        } else {
            range = 0 until board.lastIndex
            offset = 1
        }

        for (r in board.indices) {
            for (c in range) {
                val adj = c + offset
                if (board[r][c] > 0 && board[r][c] == board[r][adj]) {
                    board[r][c] *= 2
                    board[r][adj] = 0
                }
            }
        }
    }

    fun calcVertical(move: Move) {
        val range: IntProgression
        val offset: Int

        if (move.row == 1) {
            range = board.lastIndex downTo 1
            offset = -1
        } else {
            range = 0 until board.lastIndex
            offset = 1
        }

        for (c in board.indices) {
            for (r in range) {
                val adj = r + offset
                if (board[r][c] > 0 && board[r][c] == board[adj][c]) {
                    board[r][c] *= 2
                    board[adj][c] = 0
                }
            }
        }
    }

    fun attachAll(move: Move) {
        for (k in board.indices) {
            if (move.row == 0) {
                when (move.col) {
                    1 -> attach(move, k, 0)
                    else -> attach(move, k, board.lastIndex)
                }
            } else {
                when (move.row) {
                    1 -> attach(move, 0, k)
                    else -> attach(move, board.lastIndex, k)
                }
            }
        }
    }

    fun attach(move: Move, sr: Int, sc: Int) {
        var r = sr + move.row
        var c = sc + move.col

        while (r in board.indices && c in board.indices) {
            if (board[r][c] > 0) {
                attach(move, r, c)
            }
            if (board[r][c] > 0) break

            r += move.row
            c += move.col
        }
        r -= move.row
        c -= move.col

        if (r != sr || c != sc) {
            board[r][c] = board[sr][sc]
            board[sr][sc] = 0
        }
    }

    fun max() = board.maxOf { it.max() }
}

data class Move(val row: Int, val col: Int)