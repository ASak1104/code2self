class MyCircularQueue(k: Int) {

    val queue = IntArray(k + 1) { -1 }

    var front = 0
    var rear = 0

    fun enQueue(value: Int): Boolean {
        if (isFull()) return false

        rear = next(rear)
        queue[rear] = value

        return true
    }

    fun deQueue(): Boolean {
        if (isEmpty()) return false

        front = next(front)
        queue[front] = -1

        return true
    }

    fun Front(): Int {
        return queue[next(front)]
    }

    fun Rear(): Int {
        return queue[rear]
    }

    fun isEmpty(): Boolean {
        return front == rear
    }

    fun isFull(): Boolean {
        return front == next(rear)
    }

    fun next(idx: Int) = (idx + 1) % queue.size
}
