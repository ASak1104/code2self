import java.io.StreamTokenizer
import kotlin.math.abs

val city = Array(50) { IntArray(50) }
val houses = ArrayList<Node>(100)
val kfcs = ArrayList<Node>(13)

var m = 0
var n = 0
var res = Int.MAX_VALUE

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    n = readInt()
    m = readInt()

    for (r in 0 until n) {
        for (c in 0 until n) {
            city[r][c] = readInt()

            when (city[r][c]) {
                1 -> houses += Node(r, c)
                2 -> kfcs += Node(r, c)
            }
        }
    }

    travel(0, 0, 0)

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}

fun travel(start: Int, count: Int, visit: Int) {
    if (count == m) {
        var sum = 0

        for (house in houses) {
            var min = n shl 1

            for (i in kfcs.indices) {
                val mask = 1 shl i

                if (visit and mask == 0) continue

                min = minOf(min, dist(house, kfcs[i]))
            }

            sum += min
        }

        if (res > sum) res = sum
    }

    for (i in start until kfcs.size) {
        travel(i + 1, count + 1, visit or (1 shl i))
        travel(i + 1, count, visit)
    }
}

fun dist(u: Node, v: Node): Int {
    return abs(u.r - v.r) + abs(u.c - v.c)
}

class Node(val r: Int, val c: Int)