import java.util.*

fun main() {
    val (n, m) = StringTokenizer(readln()).run { nextToken().toInt() to nextToken().toInt() }
    val bw = System.out.bufferedWriter()

    fun travel(st: List<Int>, k: Int) {
        if (st.size == m) {
            bw.append(st.joinToString(" "))
            bw.newLine()
            return
        }
        if (k > n) return

        travel(st + k, k + 1)
        travel(st, k + 1)
    }
    travel(emptyList(), 1)

    bw.flush()
    bw.close()
}