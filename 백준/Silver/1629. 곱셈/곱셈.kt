import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    var a = readInt()
    val b = readInt()
    val c = readInt()

    a %= c

    fun travel(k: Long, b: Int): Long {
        if (b == 0) return k

        if (b == 1) return (k * a) % c

        return if (b and 1 == 1)
            (travel((k * k) % c, b ushr 1) * k) % c
        else
            travel((k * k) % c, b ushr 1)
    }

    val res = travel(a.toLong(), b - 1)

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}