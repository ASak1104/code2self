import java.util.*

var cap = 1
lateinit var tree: IntArray

fun main() {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()

    while (cap < n) cap *= 2

    st = StringTokenizer(br.readLine())
    tree = IntArray(cap shl 1)

    for (i in 0 until n) {
        tree[cap + i] = st.nextToken().toInt()
    }

    for (node in cap - 1 downTo 1) {
        val child = node shl 1

        tree[node] = minOf(tree[child], tree[child + 1])
    }

    val sb = StringBuilder()

    for (e in 0 until n) {
        val s = maxOf(0, e - l + 1)

        sb.append(query(s, e)).append(' ')
    }

    println(sb)
}

fun query(qs: Int, qe: Int): Int {
    var ret = Int.MAX_VALUE
    var s = cap + qs
    var e = cap + qe

    while (s < e) {
        if (s and 1 == 1) {
            ret = minOf(ret, tree[s++])
        }

        if (e and 1 == 0) {
            ret = minOf(ret, tree[e--])
        }

        ret = minOf(ret, tree[s], tree[e])
        s = s ushr 1
        e = e ushr 1
    }

    if (s == e) {
        ret = minOf(ret, tree[s])
    }

    return ret
}