import java.util.*

var n = 0
lateinit var tree: LongArray
lateinit var lazy: LongArray

fun main() {
    val br = System.`in`.bufferedReader()
    var st: StringTokenizer

    n = br.readLine().toInt()

    var cap = 1

    while (cap < n) cap *= 2

    tree = LongArray(cap shl 1)
    lazy = LongArray(cap shl 1)

    st = StringTokenizer(br.readLine())

    for (i in 1..n) {
        insert(1, 1, n, i, st.nextToken().toInt())
    }

    val m = br.readLine().toInt()
    val sb = StringBuilder()

    repeat(m) {
        st = StringTokenizer(br.readLine())

        val cmd = st.nextToken().toInt()

        if (cmd == 1) {
            val s = st.nextToken().toInt()
            val e = st.nextToken().toInt()
            val v = st.nextToken().toInt()

            update(1, 1, n, s, e, v)
        } else {
            val x = st.nextToken().toInt()

            sb.append(query(1, 1, n, x)).append('\n')
        }
    }

    print(sb)
}

tailrec fun insert(node: Int, s: Int, e: Int, x: Int, v: Int) {
    if (s == e) {
        tree[node] = v.toLong()
        return
    }

    val mid = (s + e) ushr 1
    val child = node shl 1

    if (x <= mid) {
        insert(child, s, mid, x, v)
    } else {
        insert(child + 1, mid + 1, e, x, v)
    }
}

fun update(node: Int, s: Int, e: Int, qs: Int, qe: Int, v: Int) {
    if (s > qe || e < qs) return

    if (qs <= s && e <= qe) {
        lazy[node] += v.toLong()
        return
    }

    val mid = (s + e) ushr 1
    val child = node shl 1

    update(child, s, mid, qs, qe, v)
    update(child + 1, mid + 1, e, qs, qe, v)
}

tailrec fun query(node: Int, s: Int, e: Int, x: Int): Long {
    propagation(node, s, e)

    if (s == e) {
        return tree[node]
    }

    val mid = (s + e) ushr 1
    val child = node shl 1

    return if (x <= mid) {
        query(child, s, mid, x)
    } else {
        query(child + 1, mid + 1, e, x)
    }
}

fun propagation(node: Int, s: Int, e: Int) {
    if (lazy[node] == 0L) return

    if (s != e) {
        val child = node shl 1

        lazy[child] += lazy[node]
        lazy[child + 1] += lazy[node]
    } else {
        tree[node] += lazy[node]
    }

    lazy[node] = 0L
}