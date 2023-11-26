var string = ""
var n = 0
var aCount = 0
var bCount = 0

fun main() {
    val br = System.`in`.bufferedReader()

    string = br.readLine()
    n = string.length

    aCount = string.count { c -> c == 'a' }
    bCount = n - aCount

    var minDiff = n

    repeat(n) {
        minDiff = minOf(minDiff, getDiffCount(it))
    }

    println(minDiff / 2)
    br.close()
}

fun getDiffCount(start: Int): Int {
    var diffCount = 0
    var aRemain = aCount
    var bRemain = bCount

    for (offset in 0 until n) {
        val index = (start + offset) % n

        if (aRemain > 0) {
            if (string[index] != 'a') {
                diffCount++
            }

            aRemain--
            continue
        }

        if (string[index] == 'a') {
            diffCount++
        }

        bRemain--
    }

    return diffCount
}
