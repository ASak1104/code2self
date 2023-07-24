import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun readString() = with(st) {
    nextToken()
    sval!!
}

fun flush(s: String) = with(System.out.bufferedWriter()) {
    append(s)
    flush()
    close()
}

fun main() {
    val n = readInt()
    val k = readInt()

    if (k == 26) return flush("$n")

    val words = IntArray(n) {
        var mask = 0

        for (c in readString()) {
            mask = mask or (1 shl (c - 'a'))
        }

        mask
    }

    var res = 0

    fun travel(idx: Int, count: Int, visit: Int) {
        if (count > k) return

        if (count == k) {
            var ret = 0

            words.forEach {
                if (visit and it == it) ret++
            }

            if (res < ret) res = ret

            return
        }

        for (i in idx until 26) {
            val mask = 1 shl i

            if (visit and mask == 0) {
                travel(i + 1, count + 1, visit or mask)
            }
        }
    }

    travel(0, 5, 532741)

    flush("$res")
}