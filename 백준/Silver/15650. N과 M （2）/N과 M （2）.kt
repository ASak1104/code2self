import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m2) = StringTokenizer(readLine()).run { nextToken().toInt() to (nextToken().toInt() shl 1) }
    close()

    val bw = System.out.bufferedWriter()

    fun travel(st: String, k: Int) {
        if (st.length == m2) {
            bw.append(st.subSequence(1, m2))
            bw.newLine()
            return
        }
        if (k > n) return

        travel("$st $k", k + 1)
        travel(st, k + 1)
    }
    travel("", 1)

    bw.flush()
    bw.close()
}