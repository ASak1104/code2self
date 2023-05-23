import java.io.BufferedReader
import java.util.*

data class Point(var row: Int, var col: Int)

operator fun Point.plus(o: Point) = Point(row + o.row, col + o.col)

operator fun Point.plusAssign(o: Point) {
    row += o.row
    col += o.col
}

operator fun Point.minusAssign(o: Point) {
    row -= o.row
    col -= o.col
}

data class Round(val count: Int, val prevCmd: Point?, val red: Point, val blue: Point)

class Game(rows: Int, cols: Int) {
    private val pq = PriorityQueue<Round> { a, b -> a.count - b.count }
    private val table = Array(rows) { IntArray(cols) }
    private val cmds = arrayOf(Point(1, 0), Point(0, 1), Point(-1, 0), Point(0, -1))
    private val end = Point(0, 0)

    fun initGame(br: BufferedReader) {
        val red = Point(0, 0)
        val blue = Point(0, 0)
        for (row in table.indices) {
            for ((col, c) in br.readLine().withIndex()) {
                when (c) {
                    '.' -> table[row][col] = 1
                    'R' -> {
                        table[row][col] = 1
                        red.row = row
                        red.col = col
                    }
                    'B' -> {
                        table[row][col] = 1
                        blue.row = row
                        blue.col = col
                    }
                    'O' -> {
                        table[row][col] = 1
                        end.row = row
                        end.col = col
                    }
                }
            }
        }
        pq.add(Round(1, null, red, blue))
    }

    fun start(): Int {
        while (pq.isNotEmpty()) {
            val (count, prevCmd, prevRed, prevBlue) = pq.poll()
            for (cmd in cmds) {
                if (cmd == prevCmd) continue
                val red = prevRed.copy()
                val blue = prevBlue.copy()

                if (move(blue, red, cmd)) continue
                val endRed = move(red, blue, cmd)
                val endBlue = move(blue, red, cmd)
                if (endRed && !endBlue) return count

                if (count < 10)
                    pq.add(Round(count + 1, cmd, red, blue))
            }
        }
        return -1
    }

    private fun move(bead1: Point, bead2: Point, cmd: Point): Boolean {
        while (valueAt(bead1) != 0) {
            if (bead1 == end) return true
            if (bead1 == bead2) break
            bead1 += cmd
        }
        bead1 -= cmd
        return false
    }

    private fun valueAt(p: Point) = table[p.row][p.col]
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (rows, cols) = br.readLine().split(' ').map(String::toInt)
    val game = Game(rows, cols)
    game.initGame(br)
    print(game.start())
    br.close()
}