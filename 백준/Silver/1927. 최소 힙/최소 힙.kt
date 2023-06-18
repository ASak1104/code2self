import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val bw = System.out.bufferedWriter()
    val pq = PriorityQueue<Int>(10000)

    repeat(n) {
        when (val x = readLine().toInt()) {
            0 -> bw.append("${pq.poll() ?: 0}\n")
            else -> pq.add(x)
        }
    }
    close()

    bw.flush()
    bw.close()
}