import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val seg = SegmentTree(n)

    with(StringTokenizer(readLine())) {
        repeat(n) { seg.update(it, nextToken().toInt()) }
    }

    val bw = System.out.bufferedWriter()

    repeat(m) {
        val (s, e) = StringTokenizer(readLine()).run { nextToken().toInt() - 1 to nextToken().toInt() - 1 }
        bw.append("${seg.query(s, e)}\n")
    }
    close()

    bw.flush()
    bw.close()
}

class SegmentTree(n: Int) {
    val tree: IntArray
    var cap = 1

    init {
        while (cap < n) cap *= 2
        tree = IntArray(cap shl 1)
    }

    fun update(i: Int, v: Int) {
        var node = cap + i

        while (node > 0) {
            tree[node] += v
            node = node ushr 1
        }
    }

    fun query(qs: Int, qe: Int): Int {
        var ret = 0
        var s = cap + qs
        var e = cap + qe

        while (s < e) {
            if (s and 1 == 1) ret += tree[s++]
            if (e and 1 == 0) ret += tree[e--]

            s = s ushr 1
            e = e ushr 1
        }
        if (s == e) ret += tree[s]

        return ret
    }
}