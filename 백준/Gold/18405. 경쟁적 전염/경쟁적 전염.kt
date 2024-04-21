import java.util.*
import kotlin.collections.ArrayDeque

val testTube = Array(201) { IntArray(201) }
val weights = arrayOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

var N = 0
var K = 0
var S = 0
var X = 0
var Y = 0

fun main() = with(System.`in`.bufferedReader()) {
    var st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    for (r in 1..N) {
        st = StringTokenizer(readLine())

        for (c in 1..N) {
            testTube[r][c] = st.nextToken().toInt()
        }
    }

    st = StringTokenizer(readLine())

    S = st.nextToken().toInt()
    X = st.nextToken().toInt()
    Y = st.nextToken().toInt()

    travel()

    println(testTube[X][Y])

    close()
}

fun travel() {
    val dq = ArrayDeque<IntArray>()
    val ages = Array(N + 1) { IntArray(N + 1) { Int.MAX_VALUE } }

    for (r in 1..N) {
        for (c in 1..N) {
            if (testTube[r][c] != 0) {
                dq.addLast(intArrayOf(r, c, testTube[r][c], 0))
                ages[r][c] = 0
            }
        }
    }

    while (dq.isNotEmpty()) {
        val (r, c, virus, t) = dq.removeFirst()

        if (t >= S) {
            continue
        }

        for ((rw, cw) in weights) {
            val vr = r + rw
            val vc = c + cw
            val vt = t + 1

            if (vr !in 1..N || vc !in 1..N) {
                continue
            }

            if (vt > ages[vr][vc] || testTube[vr][vc] in 1..virus) {
                continue
            }

            testTube[vr][vc] = virus
            ages[vr][vc] = vt
            dq.addLast(intArrayOf(vr, vc, virus, vt))
        }
    }
}
