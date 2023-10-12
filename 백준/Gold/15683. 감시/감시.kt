import java.util.*

val cctvPoints = ArrayList<Pair<Int, Int>>()
val cctvTypes = ArrayList<Int>()
val rowWeights = arrayOf(-1, 0, 1, 0)
val colWeights = arrayOf(0, 1, 0, -1)

lateinit var cctvSights: Array<IntArray>

var minBlindCount = 8 * 8
var n = 0
var m = 0

fun main() {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    cctvSights = Array(n) { IntArray(m) }

    for (r in 0 until n) {
        st = StringTokenizer(br.readLine())

        for (c in 0 until m) {
            val info = st.nextToken().toInt()

            if (info == 0) continue

            when (info) {
                in 1..5 -> {
                    cctvPoints += r to c
                    cctvTypes += info
                }

                6 -> cctvSights[r][c] = Int.MIN_VALUE
            }

            cctvSights[r][c] += 1
        }
    }

    simulate(0)

    println(minBlindCount)

    br.close()
}

fun simulate(start: Int) {
    if (start == cctvPoints.size) {
        minBlindCount = minOf(minBlindCount, cctvSights.sumOf { row -> row.count { it == 0 } })
        return
    }

    val type = cctvTypes[start]
    val (r, c) = cctvPoints[start]

    repeat(4) {
        increaseCCTV(r, c, type, it)

        simulate(start + 1)

        decreaseCCTV(r, c, type, it)
    }
}

fun increaseCCTV(r: Int, c: Int, type: Int, dir: Int) {
    updateSight(r, c, type, dir, 1)
}

fun decreaseCCTV(r: Int, c: Int, type: Int, dir: Int) {
    updateSight(r, c, type, dir, -1)
}

fun updateSight(r: Int, c: Int, type: Int, dir: Int, w: Int) {
    when (type) {
        1 -> updateLine(r, c, rowWeights[dir], colWeights[dir], w)
        2 -> {
            updateLine(r, c, rowWeights[dir], colWeights[dir], w)
            updateLine(r, c, rowWeights[(dir + 2) % 4], colWeights[(dir + 2) % 4], w)
        }

        3 -> {
            updateLine(r, c, rowWeights[dir], colWeights[dir], w)
            updateLine(r, c, rowWeights[(dir + 3) % 4], colWeights[(dir + 3) % 4], w)
        }

        4 -> {
            updateLine(r, c, rowWeights[dir], colWeights[dir], w)
            updateLine(r, c, rowWeights[(dir + 1) % 4], colWeights[(dir + 1) % 4], w)
            updateLine(r, c, rowWeights[(dir + 2) % 4], colWeights[(dir + 2) % 4], w)
        }

        5 -> repeat(4) { updateLine(r, c, rowWeights[it], colWeights[it], w) }
    }
}

fun updateLine(r: Int, c: Int, rw: Int, cw: Int, w: Int) {
    var rn = r + rw
    var cn = c + cw

    while (isValid(rn, cn)) {
        cctvSights[rn][cn] += w
        rn += rw
        cn += cw
    }
}

fun isValid(r: Int, c: Int): Boolean {
    return r in 0 until n && c in 0 until m && cctvSights[r][c] >= 0
}
