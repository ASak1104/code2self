import java.util.*
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val bw = System.out.bufferedWriter()
    val pq = PriorityQueue(100000, compareBy<Int>(::abs).thenBy { it })

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