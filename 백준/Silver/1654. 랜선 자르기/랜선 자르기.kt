import java.util.*

var k = 0
var n = 0

lateinit var lanLines: List<Int>

fun main() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).run {
        k = nextToken().toInt()
        n = nextToken().toInt()
    }

    lanLines = List(k) { readLine().toInt() }

    val maxLength = bisect()

    println(maxLength)
    close()
}

fun bisect(): Long {
    var left = 1L
    var right = lanLines.max().toLong()
    var lastMatch = 1L

    while (left <= right) {
        val mid = (left + right) ushr 1
        val cuts = lanLines.sumOf { it / mid }

        if (cuts >= n) {
            left = mid + 1
            lastMatch = mid
        } else {
            right = mid - 1
        }
    }

    return lastMatch
}
