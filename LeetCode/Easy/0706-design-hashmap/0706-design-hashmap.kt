class MyHashMap() {

    val nodes: Array<Node> = Array(10_000) { Node(-1, -1) }

    fun put(key: Int, value: Int) {
        val hash: Int = hash(key)
        var node: Node = nodes[hash]

        while (node.key != key) {
            if (node.next == null) {
                node.next = Node(key, value)

                return
            }

            node = node.next!!
        }

        node.value = value
    }

    fun get(key: Int): Int {
        val hash: Int = hash(key)
        var node: Node? = nodes[hash]

        while (node != null && node.key != key) {
            node = node.next
        }

        return node?.value ?: -1
    }

    fun remove(key: Int) {
        val hash: Int = hash(key)
        var node: Node = nodes[hash]

        while (node.next != null) {
            if (node.next!!.key == key) {
                node.next = node.next?.next

                return
            }

            node = node.next!!
        }
    }

    fun hash(key: Int): Int = key % nodes.size

    class Node(val key: Int, var value: Int) {

        var next: Node? = null
    }

}
