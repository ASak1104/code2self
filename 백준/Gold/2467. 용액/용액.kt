import java.util.*
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val liquids = StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } }
    var res = MixedLiquid(1_000_000_000, 1_000_000_001)

    for (i in 0..n - 2) {
        val l1 = liquids[i]
        val l2 = bisect(liquids, -l1, i + 1)
        val ml = MixedLiquid(l1, l2)

        if (ml < res) res = ml
    }
    println(res)
}

fun bisect(array: IntArray, target: Int, start: Int): Int {
    var s = start
    var e = array.lastIndex

    while (s < e - 1) {
        val m = (s + e) ushr 1
        val v = array[m]

        if (v == target) return v

        if (v < target) s = m
        else e = m
    }
    return if (abs(target - array[s]) < abs(target - array[e]))
        array[s]
    else
        array[e]
}

data class MixedLiquid(val l1: Int, val l2: Int) {
    val abs = abs(l1 + l2)
    override fun toString() = "$l1 $l2"
    operator fun compareTo(o: MixedLiquid) = abs.compareTo(o.abs)
}