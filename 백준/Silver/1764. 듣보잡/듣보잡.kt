import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val set1 = mutableSetOf<String>()
    val set2 = mutableSetOf<String>()

    repeat(n) { set1.add(readLine()) }
    repeat(m) { set2.add(readLine()) }
    close()

    val res = (set1 intersect set2).sorted()

    with(System.out.bufferedWriter()) {
        append("${res.size}\n")
        res.forEach { append("$it\n") }
        flush()
        close()
    }
}