import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val people = StringTokenizer(readLine()).run {
        IntArray(n) { nextToken().toInt() }
    }
    close()

    people.sort()

    for (i in 1 until n) {
        people[i] += people[i - 1]
    }

    with(System.out.bufferedWriter()) {
        append("${people.sum()}")
        flush()
        close()
    }
}