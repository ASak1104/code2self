import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val dict = Array(n) { readLine()!! }

    var idx = 1
    val invDict = dict.associateWith { idx++ }

    val bw = System.out.bufferedWriter()

    repeat(m) {
        val query = readLine()

        when (query[0].isDigit()) {
            true -> bw.append("${dict[query.toInt() - 1]}\n")
            else -> bw.append("${invDict[query]}\n")
        }
    }
    close()
    bw.flush()
    bw.close()
}