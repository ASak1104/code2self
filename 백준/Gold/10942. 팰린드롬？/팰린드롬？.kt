import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val game = PalinGame(StringTokenizer(readLine()).run { IntArray(n) { nextToken().toInt() } })
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val st = StringTokenizer(readLine())
        val isPalin = game.query(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1)
        bw.append(if (isPalin) '1' else '0')
        bw.newLine()
    }
    bw.flush()
    bw.close()
    close()
}

class PalinGame(val seq: IntArray) {
    fun query(qs: Int, qe: Int): Boolean {
        val mid = (qs + qe) ushr 1
        return when (qe - qs and 1) {
            0 -> isPalin(mid - 1, mid + 1, qs, qe)
            else -> isPalin(mid, mid + 1, qs, qe)
        }
    }

    tailrec fun isPalin(left: Int, right: Int, qs: Int, qe: Int): Boolean {
        if (left < qs || right > qe) {
            return true
        }
        return if (seq[left] == seq[right]) {
            isPalin(left - 1, right + 1, qs, qe)
        } else {
            false
        }
    }
}