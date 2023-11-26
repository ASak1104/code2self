var string = ""
var target = 'a'
var n = 0
var targetCount = 0

fun main() {
    val br = System.`in`.bufferedReader()

    string = br.readLine()
    n = string.length

    targetCount = string.count { c -> c == 'a' }

    if (targetCount > n ushr 1) {
        targetCount = n - targetCount
        target = 'b'
    }

    var minDiff = n

    repeat(n) {
        minDiff = minOf(minDiff, getDiffCount(it))
    }

    println(minDiff)
    br.close()
}

fun getDiffCount(start: Int): Int {
    var diffCount = 0
    var index = start

    repeat(targetCount) {
        if (string[index] != target) {
            diffCount++
        }

        index = (index + 1) % n
    }

    return diffCount
}
