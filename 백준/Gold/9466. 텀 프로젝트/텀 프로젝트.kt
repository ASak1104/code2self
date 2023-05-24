import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val testCase = readLine().toInt()

    repeat(testCase) {
        val n = readLine().toInt()
        val edges = readLine().split(' ').map { it.toInt() - 1}
        val graph = Graph(n, edges)
        graph.formTeam()
        bw.write(graph.result.toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
    close()
}

class Graph(n: Int, val edges: List<Int>) {
    val visited = BooleanArray(n)
    var result = 0

    fun formTeam() {
        for ((u, v) in edges.withIndex()) {
            if (!visited[u]) travel(u, v)
        }
    }

    fun travel(u: Int, v: Int) {
        val stack = Stack<Int>()
        var curr = u
        var next = v

        while (!visited[curr]) {
            visited[curr] = true
            stack.push(curr)
            curr = next
            next = edges[curr]
        }
        if (stack.contains(curr)) {
            if (curr == next) {
                stack.pop()
            } else {
                while (stack.isNotEmpty() && stack.pop() != curr) {}
            }
        }
        result += stack.size
    }
}