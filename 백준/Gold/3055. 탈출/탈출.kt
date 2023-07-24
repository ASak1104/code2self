import java.util.*
import kotlin.collections.ArrayDeque

lateinit var map: Array<CharArray>

val dochiQ = ArrayDeque<Node>()
val waterQ = ArrayDeque<Node>()
val rowW = arrayOf(1, -1, 0, 0)
val colW = arrayOf(0, 0, 1, -1)

var n = 0
var m = 0
var t = 0
var end = false
var arrival = false

fun main() = with(System.`in`.bufferedReader()) {
	StringTokenizer(readLine()).run {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    map = Array(n) { r ->
        val line = readLine()

        for (c in 0 until m) {
            when (line[c]) {
                '*' -> waterQ.addLast(Node(r, c, 0))
                'S' -> dochiQ.addLast(Node(r, c, 0))
            }

        }

        line.toCharArray()
    }

    val start = dochiQ.first()

    map[start.r][start.c] = 'X'

    while (!end) {
        t++
        moveWater()
        moveDochi()
    }

    with(System.out.bufferedWriter()) {
        append(if (arrival) "$t" else "KAKTUS")
        flush()
        close()
    }
}

fun moveWater() {
    while (waterQ.isNotEmpty() && waterQ.first().t < t) {
        val u = waterQ.removeFirst()

        for (i in 0..3) {
            val vr = u.r + rowW[i]
            val vc = u.c + colW[i]

            if (vr !in 0 until n || vc !in 0 until m) continue

            if (map[vr][vc] == '.') {
                map[vr][vc] = '*'
                waterQ.addLast(Node(vr, vc, u.t + 1))
            }
        }
    }
}

fun moveDochi() {
    while (dochiQ.isNotEmpty() && dochiQ.first().t < t) {
        val u = dochiQ.removeFirst()

        for (i in 0..3) {
            val vr = u.r + rowW[i]
            val vc = u.c + colW[i]

            if (vr !in 0 until n || vc !in 0 until m) continue

            if (map[vr][vc] == '.') {
                map[vr][vc] = 'X'
                dochiQ.addLast(Node(vr, vc, u.t + 1))
            }

            if (map[vr][vc] == 'D') {
                arrival = true
                end = true
                break
            }
        }
    }

    if (dochiQ.isEmpty()) end = true
}

data class Node(val r: Int, val c: Int, val t: Int)