fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine()
    val p = readLine()
    val fail = getFail(p)
    val match = ArrayList<Int>()

    var j = 0

    for (i in t.indices) {
        while (j > 0 && t[i] != p[j]) j = fail[j - 1]

        if (t[i] != p[j]) continue

        when (j) {
            fail.lastIndex -> {
                match.add(i - j + 1)
                j = fail[j]
            }
            else -> j++
        }
    }

    with(System.out.bufferedWriter()) {
        append("${match.size}\n")
        match.joinTo(this, "\n")
        flush()
        close()
    }
}

fun getFail(p: String): IntArray {
    val fail = IntArray(p.length)
    var j = 0

    for (i in 1 until p.length) {
        while (j > 0 && p[i] != p[j]) j = fail[j - 1]

        if (p[i] == p[j]) j++

        fail[i] = j
    }

    return fail
}