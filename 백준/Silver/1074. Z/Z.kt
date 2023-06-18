import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, qr, qc) = StringTokenizer(readLine()).run {
        val readInt = { nextToken().toInt() }
        Triple(readInt(), readInt(), readInt())
    }
    close()

    var res = 0

    tailrec fun compress(sr: Int, sc: Int, k: Int) {
        if (k == 0) return

        for (r in sr..sr + k step k) {
            for (c in sc..sc + k step k) {
                if (qr in r until r + k && qc in c until c + k) {
                    return compress(r, c, k ushr 1)
                }
                res += k * k
            }
        }
    }

    compress(0, 0, 1 shl (n - 1))

    with(System.out.bufferedWriter()) {
        append("$res").flush()
        close()
    }
}