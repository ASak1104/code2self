import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val compressed = IntArray(n)
    val nums = StringTokenizer(readLine()).run {
        MutableList(n) { it to nextToken().toInt() }
    }
    close()

    nums.sortBy { it.second }

    var v = 0

    for (i in 1 until n) {
        val (f, s) = nums[i]

        if (s != nums[i - 1].second) v++

        compressed[f] = v
    }
    with(System.out.bufferedWriter()) {
        compressed.joinTo(this, " ")
        flush()
        close()
    }
}