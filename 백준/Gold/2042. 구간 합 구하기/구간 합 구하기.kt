import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (n, mk) = StringTokenizer(readLine()).run {
        val token = { nextToken().toInt() }
        token() to token() + token()
    }
    val st = SegmentTree(n)

    repeat(n) { st.update(it, readLine().toLong()) }

    repeat(mk) {
        val line = StringTokenizer(readLine())
        val token = { line.nextToken().toLong() }

        when (token()) {
            1L -> st.update(token().toInt() - 1, token())
            2L -> bw.append("${st.query(token().toInt() - 1, token().toInt() - 1)}\n")
        }
    }
    bw.flush()
    bw.close()
    close()
}

class SegmentTree(n: Int) {
    val tree: LongArray
    var cap = 1

    init {
        while (cap < n) cap *= 2

        tree = LongArray(cap shl 1)
    }

    fun update(i: Int, v: Long) {
        var node = cap + i
        val weight = v - tree[node]

        while (node > 0) {
            tree[node] += weight
            node = node ushr 1
        }
    }

    fun query(qs: Int, qe: Int): Long {
        var ret = 0L
        var s = qs + cap
        var e = qe + cap

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