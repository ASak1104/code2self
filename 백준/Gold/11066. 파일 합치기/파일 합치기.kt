import java.util.*

lateinit var memo: Array<IntArray>
lateinit var sums: IntArray

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val totalCast = br.readLine().toInt()

    repeat(totalCast) {
        val k = br.readLine().toInt()

        memo = Array(k + 1) { IntArray(k + 1) }
        sums = IntArray(k + 1)

        val st = StringTokenizer(br.readLine())

        for (i in 1..k) {
            sums[i] = sums[i - 1] + st.nextToken().toInt()
        }

        bw.append("${trace(1, k)}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun trace(s: Int, e: Int): Int {
    if (s == e) return 0

    if (memo[s][e] != 0) return memo[s][e]

    var cost = Int.MAX_VALUE

    for (mid in s until e) {
        cost = minOf(cost, trace(s, mid) + trace(mid + 1, e))
    }

    memo[s][e] = cost + sums[e] - sums[s - 1]

    return memo[s][e]
}