import java.util.*

lateinit var dp: Array<IntArray>
lateinit var memo: Array<IntArray>

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val totalCast = br.readLine().toInt()

    repeat(totalCast) {
        val k = br.readLine().toInt()

        dp = Array(k) { IntArray(k) }
        memo = Array(k) { IntArray(k) { -1 } }

        val st = StringTokenizer(br.readLine())

        repeat(k) {
            dp[it][it] = st.nextToken().toInt()
            memo[it][it] = 0
        }

        for (size in 1 until k) {
            for (s in 0 until k - size) {
                val e = s + size
                var min = Int.MAX_VALUE

                for (mid in s until e) {
                    min = minOf(min, dp[s][mid] + dp[mid + 1][e])
                }

                dp[s][e] = min
            }
        }

        bw.append("${trace(0, k - 1)}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun trace(s: Int, e: Int): Int {
    if (memo[s][e] != -1) return memo[s][e]

    var ret = Int.MAX_VALUE

    for (mid in s until e) {
        val sum = dp[s][mid] + dp[mid + 1][e]

        if (sum == dp[s][e]) {
            ret = minOf(ret, sum + trace(s, mid) + trace(mid + 1, e))
        }
    }

    memo[s][e] = ret

    return ret
}