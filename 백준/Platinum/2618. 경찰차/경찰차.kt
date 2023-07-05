import java.io.StreamTokenizer
import kotlin.math.abs

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = {
        nextToken()
        nval.toInt()
    }

    val n = readInt()
    val w = readInt()
    val police = Police(n, w)

    repeat(w) {
        police.addEvent(readInt(), readInt())
    }

    with(System.out.bufferedWriter()) {
        police.emergency().joinTo(this, "\n")
        flush()
        close()
    }
}

class Police(n: Int, w: Int) {
    val events = ArrayList<Point>(w + 2)
    val memo = Array(w + 2) { IntArray(w + 2) { -1 } }

    init {
        events += Point(1, 1)
        events += Point(n, n)
    }

    fun addEvent(r: Int, c: Int) {
        events += Point(r, c)
    }

    fun emergency(): ArrayList<Int> {
        val hist = ArrayList<Int>(events.size - 1)
        var car1 = 0
        var car2 = 1
        var eid = 2

        hist += moveOut(car1, car2, eid)

        while (eid < events.size) {
            val rt1 = memo[eid][car2] + dist(car1, eid)
            val rt2 = memo[car1][eid] + dist(car2, eid)

            if (rt1 < rt2) {
                hist += 1
                car1 = eid
            } else {
                hist += 2
                car2 = eid
            }

            eid++
        }

        return hist
    }

    private fun moveOut(car1: Int, car2: Int, eid: Int): Int {
        if (eid == events.size) return 0

        if (memo[car1][car2] > -1) return memo[car1][car2]

        val rt1 = moveOut(eid, car2, eid + 1) + dist(car1, eid)
        val rt2 = moveOut(car1, eid, eid + 1) + dist(car2, eid)

        memo[car1][car2] = minOf(rt1, rt2)
        
        return memo[car1][car2]
    }

    private fun dist(cid: Int, eid: Int) = events[cid].dist(events[eid])

    data class Point(val r: Int, val c: Int) {
        fun dist(o: Point) = abs(r - o.r) + abs(c - o.c)
    }
}