import java.io.StreamTokenizer

val bw = System.out.bufferedWriter()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val nodes = ArrayList<Int>()
    var token = nextToken()

    while (token != StreamTokenizer.TT_EOF) {
        nodes += nval.toInt()
        token = nextToken()
    }

    val root = Node(nodes[0])

    for (i in 1 until nodes.size) {
        root.insert(nodes[i])
    }

    root.travel()

    bw.flush()
    bw.close()
}

class Node(val u: Int) {
    var left: Node? = null
    var right: Node? = null

    fun insert(v: Int) {
        when {
            u > v -> {
                left?.insert(v) ?: run { left = Node(v) }
            }
            else -> {
                right?.insert(v) ?: run { right = Node(v) }
            }
        }
    }

    fun travel() {
        left?.travel()

        right?.travel()

        bw.append("$u\n")
    }
}