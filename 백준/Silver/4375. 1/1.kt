var targetSize = 0
var n = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val sb = StringBuilder()

    while (true) {
        n = br.readLine().toInt()
        targetSize = 1

        while (!modTarget()) {
            targetSize++
        }

        sb.append(targetSize).append('\n')

        if (!br.ready()) break
    }

    println(sb)
    br.close()
}

fun modTarget(): Boolean {
    var r = 0

    repeat(targetSize) {
        r = ((r * 10) + 1) % n
    }

    return r == 0
}