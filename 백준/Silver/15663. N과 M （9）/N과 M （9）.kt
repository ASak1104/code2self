import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    val readInt = { nextToken(); nval.toInt() }

    val n = readInt()
    val m = readInt()

    val seq = IntArray(n) { readInt() }
    val stack = ArrayDeque<Int>(m)
    val cache = mutableSetOf<String>()

    seq.sort()

    fun travel(i: Int, visit: Int) {
        if (stack.size == m) {
            val s = stack.joinToString(" ")

            if (s !in cache) {
                cache += s
                bw.append("$s\n")
            }
            return
        }

        for (j in seq.indices) {
            val bit = 1 shl j

            if (visit and bit == 0) {
                stack.addLast(seq[j])
                travel(j, visit or bit)
                stack.removeLast()
            }
        }
    }

    for (i in seq.indices) {
        stack.addLast(seq[i])
        travel(i, 1 shl i)
        stack.removeLast()
    }

    bw.flush()
    bw.close()
}