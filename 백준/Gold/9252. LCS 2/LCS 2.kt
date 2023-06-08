import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val s1 = readLine()
    val s2 = readLine()
    close()

    val lcs = LCS(s1, s2)

    val bw = System.out.bufferedWriter()
    bw.append(lcs.length.toString())

    if (lcs.length > 0) {
        bw.newLine()
        bw.append(lcs.string)
    }
    bw.flush()
    bw.close()
}

class LCS(s1: String, s2: String) {
    val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
    val length: Int
    val string: String

    init {
        for (r in 1..dp.lastIndex) {
            for (c in 1..dp[0].lastIndex) {
                dp[r][c] = if (s1[r - 1] == s2[c - 1])
                    dp[r - 1][c - 1] + 1
                else
                    max(dp[r - 1][c], dp[r][c - 1])
            }
        }
        var r = s1.length
        var c = s2.length
        length = dp[r][c]

        val sb = StringBuilder()
        while (dp[r][c] != 0) {
            if (dp[r][c] == dp[r - 1][c]) {
                r--
            } else if (dp[r][c] == dp[r][c - 1]) {
                c--
            } else {
                sb.append(s1[--r])
                c--
            }
        }
        string = sb.reverse().toString()
    }
}