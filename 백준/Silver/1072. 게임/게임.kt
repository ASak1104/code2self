import java.io.InputStreamReader
import java.io.StreamTokenizer

var st = StreamTokenizer(InputStreamReader(System.`in`))

fun readDouble() = with(st) {
    nextToken()
    nval
}

fun main() {
    val x = readDouble()
    val y = readDouble()
	val z = (100 * y / x).toInt()

    fun bisect(): Int {
        var ret = -1
        var s = 1
        var e = x.toInt()

        while (s <= e) {
            val mid = (s + e) ushr 1
            val mz = (100 * (y + mid) / (x + mid)).toInt()

            if (mz <= z) {
                s = mid + 1
            } else {
                e = mid - 1
                ret = mid
            }
        }

        return ret
    }

    val res = if (z < 99)
        bisect()
    else
        -1

    with(System.out.bufferedWriter()) {
        append("$res")
        flush()
        close()
    }
}