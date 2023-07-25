import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val athletes = IntArray(n) { readInt() }
    val sortedPairs = athletes.withIndex().sortedBy { it.value }

    for ((i, p) in sortedPairs.withIndex()) {
        athletes[p.index] = i
    }

    val st = SegmentTree(n)

    for (athlete in athletes) {
        st.update(athlete)
    }

    val ranks = st.ranks

    for (i in athletes.indices) {
        athletes[i] = ranks[athletes[i]]
    }

    with(System.out.bufferedWriter()) {
        athletes.forEach { append("$it\n") }

        flush()
        close()
    }
}

class SegmentTree(val n: Int) {
    val tree = IntArray(n shl 2)
    val ranks = IntArray(n)

    fun update(node: Int) {
        update(1, 0, n, node)
    }

    fun update(i: Int, start: Int, end: Int, node: Int) {
        tree[i]++

        if (start == end - 1) {
            ranks[node]++
            return
        }

        val child = i shl 1
        val mid = (start + end) ushr 1

        if (node in start until mid) {
            ranks[node] += tree[child + 1]

            return update(child, start, mid, node)
        }

        update(child + 1, mid, end, node)
    }
}