import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val edges = Array(n + 1) { ArrayList<Int>() }
    
    repeat(m) {
        val st = StringTokenizer(readLine())
        var count = st.nextToken().toInt()
        var prev = st.nextToken().toInt()
        while (--count > 0) {
            val curr = st.nextToken().toInt()
            edges[curr].add(prev)
            prev = curr
        }
    }
    close()

    val stack = StringBuilder()
    val visit = BooleanArray(n + 1)
    val finish = BooleanArray(n + 1)
    var isCycle = false

    fun travel(u: Int) {
        visit[u] = true

        for (v in edges[u]) {
            if (!visit[v]) {
                travel(v)
                continue
            }
            if (!finish[v]) {
                isCycle = true
                return
            }
        }
        finish[u] = true
        stack.append("$u\n")
    }

    for (u in 1..n) {
        if (!visit[u]) travel(u)
        if (isCycle) return print(0)
    }
    print(stack)
}