import java.io.StreamTokenizer

val sttk = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(sttk) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val q = readInt()
    val segTree = SegmentTree(n)

    repeat(n) {
        segTree.insert(1, n, 1, it + 1, readInt().toLong())
    }

    val sb = StringBuilder()

    repeat(q) {
        val sum = segTree.query(readInt(), readInt())

        sb.append(sum).append('\n')

        segTree.update(1, n, 1, readInt(), readInt().toLong())
    }

    print(sb)
}

class SegmentTree(val n: Int) {
    val tree = LongArray(n shl 2)

    tailrec fun insert(s: Int, e: Int, node: Int, idx: Int, v: Long) {
        tree[node] += v

        if (s == e) return

        val mid = (s + e) ushr 1
        val child = node shl 1

        if (idx in s..mid) {
            insert(s, mid, child, idx, v)
        } else {
            insert(mid + 1, e, child + 1, idx, v)
        }
    }

    fun update(s: Int, e: Int, node: Int, idx: Int, v: Long) {
        if (idx !in s..e) return

        if (s == e) {
            tree[node] = v
            return
        }

        val mid = (s + e) ushr 1
        val child = node shl 1

        update(s, mid, child, idx, v)
        update(mid + 1, e, child + 1, idx, v)

        tree[node] = tree[child] + tree[child + 1]
    }

    fun query(s: Int, e: Int): Long {
        return if (s < e)
            query(1, n, 1, s, e)
        else
            query(1, n, 1, e, s)
    }

    private fun query(s: Int, e: Int, node: Int, qs: Int, qe: Int): Long {
        if (s > qe || e < qs) return 0L

        if (qs <= s && e <= qe) return tree[node]

        val mid = (s + e) ushr 1
        val child = node shl 1

        return query(s, mid, child, qs, qe) + query(mid + 1, e, child + 1, qs, qe)
    }
}