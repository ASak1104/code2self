import java.io.StreamTokenizer

lateinit var histogram: IntArray
lateinit var seg: SegmentTree

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    val readInt = {
        nextToken()
        nval.toInt()
    }

    var n = readInt()

    while (n > 0) {
        histogram = IntArray(n) { readInt() }
        seg = SegmentTree(n, histogram)

        bw.append("${divideAndConquer(0, n - 1)}\n")

        n = readInt()
    }

    bw.flush()
    bw.close()
}

fun divideAndConquer(s: Int, e: Int): Long {
    if (s > e) return 0

    if (s == e) return histogram[e].toLong()

    val minIndex = seg.query(s, e)

    val whole = (e - s + 1L) * histogram[minIndex]
    val left = divideAndConquer(s, minIndex - 1)
    val right = divideAndConquer(minIndex + 1, e)

    return maxOf(whole, left, right)
}

class SegmentTree(n: Int, val arr: IntArray) {
    val tree: IntArray
    var cap = 1

    init {
        while (cap < n) cap *= 2
        tree = IntArray(cap shl 1) { -1 }

        for (i in arr.indices) {
            tree[cap + i] = i
        }

        var s = cap ushr 1

        while (s > 0) {
            for (node in s until (s shl 1)) {
                tree[node] = minByValue(tree[node shl 1], tree[(node shl 1) + 1])
            }
            s = s ushr 1
        }
    }

    fun query(qs: Int, qe: Int): Int {
        var ret = -1
        var s = cap + qs
        var e = cap + qe

        while (s < e) {
            if (s and 1 == 1) ret = minByValue(ret, tree[s++])
            if (e and 1 == 0) ret = minByValue(ret, tree[e--])

            s = s ushr 1
            e = e ushr 1
        }

        if (s == e) ret = minByValue(ret, tree[s])

        return ret
    }

    fun minByValue(i: Int, j: Int) = if (get(i) <= get(j)) i else j

    fun get(i: Int) = if (i in arr.indices) arr[i] else Int.MAX_VALUE
}