import java.util.*

data class Node(val k: Int, val count: Int, val prev: Node?=null) {
    override fun toString(): String = if (this.prev == null) k.toString() else "$prev $k"
}

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val heap = PriorityQueue<Node> { a, b ->
        if (a.count == b.count) {
            a.k - b.k
        } else {
            a.count - b.count
        }
    }
    heap.add(Node(n, 0))
    while (heap.isNotEmpty()) {
        val node = heap.poll()
        if (node.k == 1) {
            print("${node.count}\n$node")
            break
        }
        if (node.k % 3 == 0 && node.k / 3 >= 1) {
            heap.add(Node(node.k / 3, node.count + 1, node))
        }
        if (node.k % 2 == 0 && node.k ushr 1 >= 1) {
            heap.add(Node(node.k ushr 1, node.count + 1, node))
        }
        if (node.k > 1) {
            heap.add(Node(node.k - 1, node.count + 1, node))
        }
    }
}