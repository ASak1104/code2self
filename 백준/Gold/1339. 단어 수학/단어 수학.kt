import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
	val variables = Array(n) { readLine() }
    val counts = IntArray(26)

    for (s in variables) {
        for ((i, c) in s.withIndex()) {
            counts[c - 'A'] += 10.0.pow(s.length - i - 1).toInt()
        }
    }

    val alphaMap = IntArray(26)
	var k = 9

    for (i in (0..25).sortedByDescending { counts[it] }) {
        alphaMap[i] = k--

        if (k == 0) break
    }

    var res = 0
    val sb = StringBuilder()

    for (variable in variables) {
        for (c in variable) {
            sb.append(alphaMap[c - 'A'])
        }

        res += sb.toString().toInt()
        sb.clear()
    }

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}