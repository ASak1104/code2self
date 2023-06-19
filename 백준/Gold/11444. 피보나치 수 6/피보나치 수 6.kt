fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toLong()
    close()

    val memo = mutableMapOf(1L to 1L, 2L to 1L, 3L to 2L)

    fun dOcagne(k: Long): Long {
        if (k !in memo) {
            var ret = 0L
            val m = k ushr 1
            val n = k - m

            ret += dOcagne(m - 1) * dOcagne(n) % 1_000_000_007
            ret += dOcagne(m) * dOcagne(n + 1) % 1_000_000_007

            memo[k] = ret % 1_000_000_007
        }
        return memo[k]!!
    }

    with(System.out.bufferedWriter()) {
        append("${dOcagne(t)}").flush()
        close()
    }
}