class SegmentTree(private val n: Int) {
    private val tree = IntArray(n * 4)
    private val ranks = IntArray(n)
    
    fun getRank(): IntArray = ranks

    fun update(node: Int) {
        update(1, 0, n, node)
    }

    private tailrec fun update(i: Int, start: Int, end: Int, node: Int) {
        tree[i]++
        if (start == end - 1) {
            ranks[node]++
            return
        }
        val child = i shl 1
        val mid = (start + end) ushr 1
        if (node in start until mid) {
            ranks[node] += tree[child + 1]
            update(child, start, mid, node)
        } else {
            update(child + 1, mid, end, node)
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
    val pairs = Array(n) { readLine().toInt() to it }
    val athletes = IntArray(n)
    for ((i, pair) in pairs.sortedBy { it.first }.withIndex()) {
        athletes[pair.second] = i
    }
    val st = SegmentTree(n)
    for (athlete in athletes) {
        st.update(athlete)
    }
    val ranks = st.getRank()
    for (i in athletes.indices) {
        athletes[i] = ranks[athletes[i]]
    }
    print(athletes.joinToString("\n"))
}