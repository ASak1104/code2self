import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val numer1 = readInt()
    val denom1 = readInt()
    val numer2 = readInt()
    val denom2 = readInt()

    val numer = numer1 * denom2 + numer2 * denom1
    val denom = denom2 * denom1
    val gcd = gcd(numer, denom)

    with(System.out.bufferedWriter()) {
        append("${numer / gcd} ${denom / gcd}")
        flush()
        close()
    }
}

fun gcd(x: Int, y: Int): Int {
    var a = x
    var b = y

    while (b > 0) {
        val r = a % b

        a = b
        b = r
    }

    return a
}