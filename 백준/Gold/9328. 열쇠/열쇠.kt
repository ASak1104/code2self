import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val t = br.readLine().toInt()

    repeat(t) {
        val (h, w) = StringTokenizer(br.readLine()).run { nextToken().toInt() to nextToken().toInt() }
        val ts = ThiefSimulator(h, w)

        ts.readMap(br)
        ts.simulate()

        bw.append(ts.docs.toString())
        bw.newLine()
    }
    br.close()
    bw.flush()
    bw.close()
}

class ThiefSimulator(val h: Int, val w: Int) {
    val moves = arrayOf(1 at 0, -1 at 0, 0 at 1, 0 at -1)
    val map = Array(h + 2) { CharArray(w + 2) { '.' } }
    val visit = Array(h + 2) { BooleanArray(w + 2) }
    val keys = mutableSetOf<Char>()
    var docs = 0

    fun readMap(br: BufferedReader) {
        for (r in 1..h) {
            val line = br.readLine()
            for (c in 1..w) {
                map[r][c] = line[c - 1]
            }
        }
        keys.addAll(br.readLine().map { it.uppercaseChar() })
    }

    fun simulate() {
        var before: Int
        do {
            before = keys.size
            travel()
        } while (before != keys.size)
    }

    fun travel() {
        visit.forEach { it.fill(false) }
        val queue = ArrayDeque<Point>()
        queue.addLast(0 at 0)

        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()

            if (visit[curr.r][curr.c]) continue

            visit[curr.r][curr.c] = true

            for (move in moves) {
                val next = curr + move

                if (next.invalid()) continue

                val target = map[next.r][next.c]

                when {
                    target == '.' -> {
                        queue.addLast(next)
                    }
                    target == '$' -> {
                        docs++
                        map[next.r][next.c] = '.'
                        queue.addLast(next)
                    }
                    target.isLowerCase() -> {
                        keys.add(target.uppercaseChar())
                        map[next.r][next.c] = '.'
                        queue.addLast(next)
                    }
                    target in keys -> {
                        map[next.r][next.c] = '.'
                        queue.addLast(next)
                    }
                }
            }
        }
    }

    data class Point(val r: Int, val c: Int) {
        operator fun plus(o: Point) = Point(r + o.r, c + o.c)
    }

    fun Point.invalid() = r !in map.indices || c !in map[0].indices || map[r][c] == '*'

    infix fun Int.at(o: Int) = Point(this, o)
}