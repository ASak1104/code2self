import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val a = readInt().toLong()
    val b = readInt()
    val c = readInt()

    fun travel(k: Long, exp: Int): Long {
        if (exp == 1) return k

        val pow = (k * k) % c

        return if (exp and 1 == 1)
            travel(pow, exp ushr 1) * k % c
        else
            travel(pow, exp ushr 1)
    }

    val res = travel(a % c, b)

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}