import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array(n + 1) { ArrayList<Int>() }
    
    repeat(m) {
        StringTokenizer(readLine()).apply {
            val small = nextToken().toInt()
            val big = nextToken().toInt()
            edges[big].add(small)
        }
    }
    close()

    val visit = BooleanArray(n + 1)
    val bw = System.out.bufferedWriter()

    fun travel(u: Int) {
        visit[u] = true

        for (v in edges[u]) {
            if (!visit[v]) travel(v)
        }
        bw.append("$u ")
    }

    for (u in 1..n) {
        if (!visit[u]) travel(u)
    }
    bw.flush()
    bw.close()
}