import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val seg = SegmentTree(n)

    repeat(n) { seg.update(it, readLine().toInt()) }

    repeat(m) {
        val (s, e) = StringTokenizer(readLine()).run { nextToken().toInt() - 1 to nextToken().toInt() - 1 }
        bw.append("${seg.query(s, e)}\n")
    }
    bw.flush()
    bw.close()
    close()
}

class SegmentTree(n: Int) {
    val MAX = 1_000_000_001
    val tree: Array<Node>

    var cap = 1

    init {
        while (cap < n) cap *= 2

        tree = Array(cap shl 1) { Node(MAX, 0) }
    }

    fun update(i: Int, v: Int) {
        var node = cap + i

        while (node > 0) {
            tree[node].update(v)
            node = node ushr 1
        }
    }

    fun query(qs: Int, qe: Int): Node {
        val ret = Node(MAX, 0)
        var s = qs + cap
        var e = qe + cap

        while (s < e) {
            if (s and 1 == 1) ret.update(tree[s++])

            if (e and 1 == 0) ret.update(tree[e--])

            s = s ushr 1
            e = e ushr 1
        }
        if (s == e) ret.update(tree[s])

        return ret
    }

    class Node (var v1: Int, var v2: Int) {
        fun update(v: Int) {
            v1 = min(v1, v)
            v2 = max(v2, v)
        }

        fun update(o: Node) {
            v1 = min(v1, o.v1)
            v2 = max(v2, o.v2)
        }

        override fun toString() = "$v1 $v2"
    }
}