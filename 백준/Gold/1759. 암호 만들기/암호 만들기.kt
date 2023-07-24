import java.util.*

val bw = System.out.bufferedWriter()
lateinit var chars: ArrayList<Char>
val vowels = arrayOf('a', 'e', 'i', 'o', 'u')
var L = 0
var C = 0

fun main() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).run {
        L = nextToken().toInt()
        C = nextToken().toInt()
    }

    chars = ArrayList<Char>(C)

    readLine().forEach {
        if (it != ' ') chars += it
    }

    chars.sort()

	travel(0, 0, 0, 0)

    bw.flush()
    bw.close()
}

fun travel(start: Int, cc: Int, vc: Int, visit: Int) {
    if (cc + vc == L) {
        if (cc < 2 || vc == 0) return

        for (i in 0 until C) {
            val mask = 1 shl i

            if (visit and mask == mask) {
                bw.append(chars[i])
            }
        }

        bw.newLine()

        return
    }

    for (i in start until C) {
        val mask = 1 shl i

        if (visit and mask == 0) {
            if (chars[i] in vowels) {
                travel(i + 1, cc, vc + 1, visit or mask)
            } else {
                travel(i + 1, cc + 1, vc, visit or mask)
            }
        }
    }
}