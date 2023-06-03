import java.util.*

val lis = arrayListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val seq = IntArray(n) { st.nextToken().toInt() }
    lis.add(seq[0])

    for (i in 1 until n) {
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