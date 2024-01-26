import java.util.*

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

var n = 0
var m = 0
var k = 0

lateinit var cashes: IntArray

fun main() {
    val tc = br.readLine().toInt()

    repeat(tc) {
        with(StringTokenizer(br.readLine())) {
            n = nextToken().toInt()
            m = nextToken().toInt()
            k = nextToken().toInt()
        }

        with(StringTokenizer(br.readLine())) {
            cashes = IntArray(n) { nextToken().toInt() }
        }

        bw.append("${simulate()}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun simulate(): Int {
    if (m == n) {
        return if (cashes.sum() < k)
            1
        else
            0
    }

    var count = 0
    var benifit = 0

    for (i in 0 until m - 1) {
        benifit += cashes[i]
    }

    for (left in cashes.indices) {
        benifit += cashes[(left + n + m - 1) % n]

        if (benifit < k) {
            count++
        }

        benifit -= cashes[left]
    }

    return count
}
