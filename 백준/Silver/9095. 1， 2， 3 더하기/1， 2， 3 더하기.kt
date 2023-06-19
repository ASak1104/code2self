fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val res = IntArray(t)

    fun travel(t: Int, n: Int, k: Int) {
        if (k > n) return

        if (k == n) {
            res[t]++
            return
        }

        for (i in 1..3) travel(t, n, k + i)
    }

    repeat(t) { travel(it, readLine().toInt(), 0) }
    close()

    with(System.out.bufferedWriter()) {
        res.forEach { append("$it\n") }
        flush()
        close()
    }
}