class SPF {
    private val vertices = mutableSetOf<Int>()
    private val edges = Array(1001) { BooleanArray(1001) }
    private var net = 1

    fun setEdge(u: Int, v: Int) {
        vertices.add(u)
        vertices.add(v)
        edges[u][v] = true
        edges[v][u] = true
    }

    fun clear() {
        for (r in vertices) {
            edges[r].fill(false)
        }
        vertices.clear()
        net++
    }

    fun appendSPF(sb: StringBuilder) {
        sb.append("Network #$net\n")
        var count = 0

        for (u in vertices) {
            val tree = findSPF(u)
            if (tree > 1) {
                sb.append("  SPF node $u leaves $tree subnets\n")
                count++
            }
        }

        if (count == 0) {
            sb.append("  No SPF nodes\n")
        }
    }

    private fun findSPF(node: Int): Int {
        val visitSet = mutableSetOf<Int>()
        visitSet.add(node)
        var tree = 0

        for (u in vertices) {
            if (u !in visitSet) {
                travel(u, visitSet)
                tree++
            }
        }
        return tree
    }

    private fun travel(u: Int, visit: MutableSet<Int>) {
        visit.add(u)
        for (v in vertices) {
            if (v !in visit && edges[u][v]) {
                travel(v, visit)
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val spf = SPF()
    val sb = StringBuilder()

    do {
        var input = readLine()
        if (input == "0") break

        while (input != "0") {
            val (u, v) = input.split(" ").map { it.toInt() }
            spf.setEdge(u, v)
            input = readLine()
        }
        spf.appendSPF(sb)
        sb.append("\n")
        spf.clear()
    } while (readLine() == "")

    print(sb.trimEnd())
}