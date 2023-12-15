class MyCircularQueue(val k: Int) {

    var front: Node? = null
    var rear: Node? = null
    var count = 0

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false

        if (isEmpty()) {
            front = Node(value)
            rear = front
        } else {
            rear!!.next = Node(value)
            rear = rear!!.next
        }

        count++

        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false

        front = front!!.next
        count--

        if (isEmpty()) {
            rear = null
        }

        return true
    }

    fun Front(): Int {
        return front?.value ?: -1
    }

    fun Rear(): Int {
        return rear?.value ?: -1
    }

    fun isEmpty(): Boolean {
        return count == 0
    }

    fun isFull(): Boolean {
        return count == k
    }

    class Node(val value: Int) {

        var next: Node? = null
    }
}
