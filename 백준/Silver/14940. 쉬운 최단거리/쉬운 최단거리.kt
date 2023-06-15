import java.util.*

val br = System.`in`.bufferedReader()

fun main() {
    val (n, m) = StringTokenizer(br.readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val bfs = BFS(n, m)
    br.close()

    bfs.travel()

    with(System.out.bufferedWriter()) {
        bfs.map.forEach {
            it.joinTo(this, " ")
            newLine()
        }
        flush()
        close()
    }
}

class BFS(val n: Int, val m: Int) {
    val weights = arrayOf(1 at 0, -1 at 0, 0 at 1, 0 at -1)
    val map = Array(n) { IntArray(m) }
    val visit = Array(n) { BooleanArray(m) }
    lateinit var start: Point

    init {
        repeat(n) { r ->
            val st = StringTokenizer(br.readLine())

            repeat(m) { c ->
                val v = st.nextToken().toInt()
                map[r][c] = v
                visit[r][c] = v == 0

                if (v == 2) start = r at c
            }
        }
    }

    fun travel() {
        val queue = Array<Point?>(n * m) { null }
        var front = 0
        var rear = 0

        queue[rear++] = start
        map[start.r][start.c] = 0
        visit[start.r][start.c] = true

        while (front < rear) {
            val u = queue[front++] ?: error("empty queue")

            for (w in weights) {
                val v = u + w

                if (v.valid()) {
                    visit[v.r][v.c] = true
                    map[v.r][v.c] = v.w
                    queue[rear++] = v
                }
            }
        }
        repeat(n) { r -> repeat(m) { c -> if (!visit[r][c]) map[r][c] = -1 } }
    }

    data class Point(val r: Int, val c: Int, val w: Int) {
        operator fun plus(o: Point) = Point(r + o.r, c + o.c, w + 1)
    }

    fun Point.valid() = r in 0 until n && c in 0 until m && !visit[r][c]

    infix fun Int.at(o: Int) = Point(this, o, 0)
}