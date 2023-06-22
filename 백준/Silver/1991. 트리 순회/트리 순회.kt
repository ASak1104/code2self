import java.io.StreamTokenizer

val bw = System.out.bufferedWriter()

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    val readInt = { nextToken(); nval.toInt() }
    val read = { nextToken(); sval?.get(0) }
    val n = readInt()
    val nodes = ('A' until 'A' + n).associateWith { Node(it) }

    repeat(n) {
        val node = read()
        val left = read()
        val right = read()

        nodes[node]?.left = nodes[left]
        nodes[node]?.right = nodes[right]
    }

    val root = nodes['A']

    preorder(root)
    bw.newLine()

    inorder(root)
    bw.newLine()

    postorder(root)
    bw.flush()
    bw.close()
}

fun preorder(node: Node?) {
    if (node == null) return

    bw.append(node.char)
    preorder(node.left)
    preorder(node.right)
}

fun inorder(node: Node?) {
    if (node == null) return

    inorder(node.left)
    bw.append(node.char)
    inorder(node.right)
}

fun postorder(node: Node?) {
    if (node == null) return

    postorder(node.left)
    postorder(node.right)
    bw.append(node.char)
}

class Node(val char: Char) {
    var left: Node? = null
    var right: Node? = null
}