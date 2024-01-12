import java.io.InputStreamReader
import java.io.StreamTokenizer

val st = StreamTokenizer(InputStreamReader(System.`in`))

var n = 0
var m = 0
var w = 0

lateinit var edges: ArrayList<Triple<Int, Int, Int>>

fun readInt() = st.run {
    nextToken()
    nval.toInt()
}

fun main() {
    val bw = System.out.bufferedWriter()
    val tc = readInt()

    repeat(tc) {
        bw.append(checkNegativeCycle())
    }

    bw.flush()
    bw.close()
}

fun checkNegativeCycle(): String {
    n = readInt()
    m = readInt()
    w = readInt()
    edges = ArrayList(m)

    repeat(m) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = readInt()

        edges.add(Triple(u, v, w))
        edges.add(Triple(v, u, w))
    }

    repeat(w) {
        val u = readInt() - 1
        val v = readInt() - 1
        val w = -readInt()

        edges.add(Triple(u, v, w))
    }

    val dist = IntArray(n)

    repeat(n) {
        for ((u, v, w) in edges) {
            if (dist[v] <= dist[u] + w) {
                continue
            }

            dist[v] = dist[u] + w

            if (it == n - 1) {
                return "YES\n"
            }
        }
    }

    return "NO\n"
}
