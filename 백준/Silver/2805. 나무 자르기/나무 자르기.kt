import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())
val tree = IntArray(1_000_000)
var n = 0
var m = 0L

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    n = readInt()
    m = readInt().toLong()

    repeat(n) { tree[it] = readInt() }

    with(System.out.bufferedWriter()) {
        append("${bisect()}")
        flush()
        close()
    }
}

fun logging(h: Int): Long {
    var ret = 0L

    for (i in 0 until n) {
        if (tree[i] > h) {
            ret += tree[i] - h
        }
    }

    return ret
}

fun bisect(): Int {
    var s = 0
    var e = 1e9.toInt()

    while (s <= e) {
        val mid = (s + e) ushr 1
		val log = logging(mid)

        if (log == m) return mid

		if (log > m) {
            s = mid + 1
        } else {
            e = mid - 1
        }
    }

    return e
}