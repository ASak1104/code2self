import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val st = StringTokenizer(readLine())
    val seq = List(n) { st.nextToken().toInt() }
    close()

    val leftSum = SeqSum(seq.subList(0, n ushr 1)).sums
    val rightSum = SeqSum(seq.subList(n ushr 1, n)).sums
    var res = (leftSum[s] ?: 0) + (rightSum[s] ?: 0).toLong()

    for ((k, v) in leftSum.entries) {
        res += v * (rightSum[s - k] ?: 0).toLong()
    }
    print(res)
}

class SeqSum(val seq: List<Int>) {
    val sums = mutableMapOf<Int, Int>()

    init {
        travel(0, 0)
    }

    fun travel(i: Int, prev: Int) {
        if (i == seq.size) return

        val next = prev + seq[i]
        sums[next] = (sums[next] ?: 0) + 1

        travel(i + 1, prev)
        travel(i + 1, next)
    }
}