import java.util.*
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val game = Game(n, m)

    game.readBoard(Array(n) { readLine() })
    game.simulate()

    with(System.out.bufferedWriter()) {
        append("${game.result}")
        flush()
        close()
    }

    close()
}

class Game(val n: Int, val m: Int) {
    val board = Array(n) { CharArray(m) }
    val rowW = intArrayOf(1, -1, 0, 0)
    val colW = intArrayOf(0, 0, 1, -1)
    var result = 0

    fun readBoard(array: Array<String>) {
        repeat(n) { r ->
            repeat(m) { c ->
                board[r][c] = array[r][c]
            }
        }
    }

    fun simulate() {
        val queue = ArrayDeque<Node>()
        val visit = Array(n) { IntArray(m) }

        queue.addLast(Node(0, 0, 1))
        visit[0][0] = 1

        while (queue.isNotEmpty()) {
            val u = queue.removeFirst()

            if (u.count > n * m) {
                result = -1
                return
            }

            result = u.count

            for (i in rowW.indices) {
                val weight = board[u.r][u.c] - '0'
                val vr = u.r + rowW[i] * weight
                val vc = u.c + colW[i] * weight

                if (vr !in 0 until n || vc !in 0 until m) continue

                if (board[vr][vc] == 'H' || visit[vr][vc] > u.count) continue

                queue.addLast(Node(vr, vc, u.count + 1))
                visit[vr][vc] = u.count + 1
            }
        }
    }

    data class Node(val r: Int, val c: Int, val count: Int)
}