import java.util.*

val bw = System.out.bufferedWriter()
lateinit var alphas: ArrayList<Char>
val vowels = setOf('a', 'e', 'i', 'o', 'u')
var L = 0
var C = 0

fun main() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).run {
        L = nextToken().toInt()
        C = nextToken().toInt()
    }

    alphas = ArrayList<Char>(C)

    readLine().forEach {
        if (it.isLetter()) alphas += it
    }

    alphas.sort()

	travel(0, 0, 0, 0)

    bw.flush()
    bw.close()
}

fun travel(start: Int, cnt: Int, vc: Int, visit: Int) {
    if (cnt == L) {
        if (cnt - vc < 2 || vc == 0) return

        val sb = StringBuilder()

        for (i in 0 until C) {
            val mask = 1 shl i

            if (visit and mask == mask) {
                sb.append(alphas[i])
            }
        }

        sb.append("\n")
        bw.append(sb)

        return
    }

    for (i in start until C) {
        val mask = 1 shl i

        if (visit and mask == 0) {
            val nvc = if (alphas[i] in vowels) vc + 1 else vc

            travel(i + 1, cnt + 1, nvc, visit or mask)
        }
    }
}