
import java.io.StreamTokenizer
import kotlin.math.abs

val dists = Array(100) { IntArray(13) }
val houses = Array(100) { Node(0, 0) }
val kfcs = Array(13) { Node(0, 0) }

var m = 0
var n = 0
var hsize = 0
var ksize = 0
var res = Int.MAX_VALUE

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    n = readInt()
    m = readInt()

    repeat(n) { r ->
        repeat(n) { c->
            when (readInt()) {
                1 -> houses[hsize++].set(r, c)
                2 -> kfcs[ksize++].set(r, c)
            }
        }
    }

    repeat(hsize) { i ->
        repeat(ksize) { j ->
            dists[i][j] = abs(houses[i].r - kfcs[j].r) + abs(houses[i].c - kfcs[j].c)
        }
    }

    travel(0, ArrayDeque(m))

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}

fun travel(start: Int, visit: ArrayDeque<Int>) {
    if (visit.size == m) {
        var sum = 0

        for (i in 0 until hsize) {
            var min = n shl 1

            for (j in visit) {
                if (min > dists[i][j]) min = dists[i][j]
            }

            sum += min
        }

        if (res > sum) res = sum

        return
    }

    for (i in start until ksize) {
        visit.addLast(i)

        travel(i + 1, visit)

        visit.removeLast()
    }
}

class Node(var r: Int, var c: Int) {
    fun set(r: Int, c: Int) {
        this.r = r
        this.c = c
    }
}