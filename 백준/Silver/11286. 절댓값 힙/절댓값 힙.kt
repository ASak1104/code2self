import java.util.*
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val bw = System.out.bufferedWriter()
    val pq = PriorityQueue<Int>(10000) { a, b ->
        when (abs(a)) {
            abs(b) -> a - b
            else -> abs(a) - abs(b)
        }
    }

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