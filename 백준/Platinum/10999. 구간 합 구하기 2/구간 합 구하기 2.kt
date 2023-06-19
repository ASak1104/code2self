import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val (n, mk) = StringTokenizer(readLine()).run {
        val token = { nextToken().toInt() }
        token() to token() + token()
    }

    val st = SegmentTree(n)

    repeat(n) { st.insert(1, 0, n - 1, it, readLine().toLong()) }

    repeat(mk) {
        val line = StringTokenizer(readLine())
        val token = { line.nextToken().toInt() - 1 }

        when (token()) {
            0 -> st.updateRange(1, 0, n - 1, token(), token(), line.nextToken().toLong())
            else -> bw.append("${st.query(1, 0, n - 1, token(), token())}\n")
        }
    }
    close()

    bw.flush()
    bw.close()
}

class SegmentTree(n: Int) {
    val tree = LongArray(n shl 2)
    val lazy = LongArray(tree.size)

    tailrec fun insert(node: Int, s: Int, e: Int, i: Int, v: Long) {
        if (i !in s..e) return

        tree[node] += v

        if (s == e) return

        val child = node shl 1
        val mid = (s + e) ushr 1

        return if (i <= mid)
            insert(child, s, mid, i, v)
        else
            insert(child + 1, mid + 1, e, i, v)
    }

    fun updateRange(node: Int, s: Int, e: Int, qs: Int, qe: Int, v: Long) {
        propagation(node, s, e)

        if (s > qe || e < qs) return

        val child = node shl 1

        if (qs <= s && e <= qe) {
            tree[node] += (e - s + 1) * v

            if (s != e) {
                lazy[child] += v
                lazy[child + 1] += v
            }
            return
        }

        val mid = (s + e) ushr 1

        updateRange(child, s, mid, qs, qe, v)
        updateRange(child + 1, mid + 1, e, qs, qe, v)

        tree[node] = tree[child] + tree[child + 1]
    }

    fun query(node: Int, s: Int, e: Int, qs: Int, qe: Int): Long {
        propagation(node, s, e)

        if (s > qe || e < qs) return 0

        if (qs <= s && e <= qe) {
            return tree[node]
        }
        val child = node shl 1
        val mid = (s + e) ushr 1

        return query(child, s, mid, qs, qe) + query(child + 1, mid + 1, e, qs, qe)
    }

    fun propagation(node: Int, s: Int, e: Int) {
        if (lazy[node] != 0L) {
            tree[node] += (e - s + 1) * lazy[node]

            if (s != e) {
                val child = node shl 1

                lazy[child] += lazy[node]
                lazy[child + 1] += lazy[node]
            }
            lazy[node] = 0
        }
    }
}