import kotlin.math.min

class SegmentTree(private val n: Int) {
    private val tree = IntArray(n * 4 + 1) { Int.MAX_VALUE }

    fun append(idx: Int, value: Int) {
        update(1, 1, n, idx, value)
    }

    private fun update(node: Int, start: Int, end: Int, idx: Int, value: Int) {
        if (idx !in start..end) {
            return
        }
        if (start == end) {
            tree[node] = value
            return
        }
        val child = node shl 1
        val mid = (start + end) ushr 1
        update(child, start, mid, idx, value)
        update(child + 1, mid + 1, end, idx, value)
        tree[node] = min(tree[child], tree[child + 1])
    }

    fun query(left: Int, right: Int): Int {
        return query(1, 1, n, left, right)
    }

    private fun query(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
        if (start > right || end < left) {
            return Int.MAX_VALUE
        }
        if (left <= start && end <= right) {
            return tree[node]
        }
        val child = node shl 1
        val mid = (start + end) ushr 1
        val leftChild = query(child, start, mid, left, right)
        val rightChild = query(child + 1, mid + 1, end, left, right)
        return min(leftChild, rightChild)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    val values = IntArray(n) { readLine().toInt() }
    val st = SegmentTree(n)
    for ((idx, value) in values.withIndex()) {
        st.append(idx + 1, value)
    }
    val sb = StringBuilder()
    for (i in 1..m) {
        val (left, right) = readLine().split(' ').map { it.toInt() }
        sb.append(st.query(left, right), "\n")
    }
    print(sb.trimEnd())
}