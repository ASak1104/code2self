import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val bw = System.out.bufferedWriter()
    val readInt = {
        nextToken()
        nval.toInt()
    }

    repeat(readInt()) {
        val n = readInt()
        val parents = IntArray(n + 1)

        repeat(n - 1) {
            val v = readInt()
            val u = readInt()

            parents[u] = v
        }

        val tree = Tree(n, parents)

        bw.append("${tree.query(readInt(), readInt())}\n")
    }
    bw.flush()
    bw.close()
}

class Tree(n: Int, val parents: IntArray) {
    val visit = BooleanArray(n + 1)
    var root = 0

    init {
        for (i in 1..n) {
            if (parents[i] == 0) {
                root = i
                break
            }
        }
    }

    fun query(qu: Int, qv: Int): Int {
        climbTree(qu)
        return climbTree(qv)
    }

    fun climbTree(u: Int): Int {
        if (visit[u]) return u

        visit[u] = true

        if (u == root) return 0

        return climbTree(parents[u])
    }
}