import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val set = HashSet<Int>(n)

    repeat(n) { set += readInt() }

    with(System.out.bufferedWriter()) {
        repeat(readInt()) {
            append(if (readInt() in set) "1\n" else "0\n")
        }

        flush()
        close()
    }
}