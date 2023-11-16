import java.util.*

lateinit var stocks: IntArray

var n = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val testCase = br.readLine().toInt()

    repeat(testCase) {
        n = br.readLine().toInt()
        stocks = StringTokenizer(br.readLine()).run { IntArray(n) { nextToken().toInt() } }

        bw.append("${calc()}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun calc(): Long {
    var profit = 0L
    var right = n - 1

    while (right > 0) {
        var left = right - 1

        while (left >= 0 && stocks[left] <= stocks[right]) {
            profit += stocks[right] - stocks[left]
            left--
        }

        right = left
    }

    return profit
}
