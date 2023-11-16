import java.util.*
import kotlin.collections.ArrayDeque

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

lateinit var edges: Array<ArrayList<Int>>
lateinit var visits: BooleanArray

var n = 0
var m = 0
var start = 0

fun main() {
    with(StringTokenizer(br.readLine())) {
        n = nextToken().toInt()
        m = nextToken().toInt()
        start = nextToken().toInt()
    }

    edges = Array(n + 1) { arrayListOf() }

    repeat(m) {
        with(StringTokenizer(br.readLine())) {
            val u = nextToken().toInt()
            val v = nextToken().toInt()

            edges[u] += v
            edges[v] += u
        }
    }

    edges.forEach { edge -> edge.sort() }
    visits = BooleanArray(n + 1)

    dfs(start)

    bw.newLine()

    visits.fill(false)

    bfs(start)

    bw.flush()
    bw.close()
    br.close()
}

fun dfs(u: Int) {
    bw.append("$u ")

    visits[u] = true

    for (v in edges[u]) {
        if (visits[v]) continue

        dfs(v)
    }
}

fun bfs(start: Int) {
    val deque = ArrayDeque<Int>(n)

    deque.addLast(start)
    visits[start] = true

    while (deque.isNotEmpty()) {
        val u = deque.removeFirst()

        bw.append("$u ")

        for (v in edges[u]) {
            if (visits[v]) continue

            deque.addLast(v)
            visits[v] = true
        }
    }
}
