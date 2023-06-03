import java.util.*

val lis = arrayListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val seq = IntArray(n) { st.nextToken().toInt() }
    lis.add(seq[n - 1])

    for (i in n - 2 downTo 0) {
        update(seq[i])
    }
    print(lis.size)
    close()
}

fun update(target: Int) {
    if (lis[lis.lastIndex] < target) {
        lis.add(target)
    } else {
        val idx = bisect(target)
        if (lis[idx] > target) {
            lis[idx] = target
        }
    }
}

fun bisect(t: Int): Int {
    var s = 0
    var e = lis.lastIndex

    while (s < e) {
        val mid = (s + e) ushr 1
        val v = lis[mid]

        if (v == t) return mid

        if (v < t) {
            s = mid + 1
        } else {
            e = mid
        }
    }
    return e
}