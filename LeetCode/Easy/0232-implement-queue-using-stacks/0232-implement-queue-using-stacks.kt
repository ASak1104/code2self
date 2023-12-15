import java.util.*

class MyQueue() {

    var onlyOne = Stack<Int>()
    var buffer = Stack<Int>()

    fun push(x: Int) {
        val target = if (onlyOne.isEmpty())
            onlyOne
        else
            buffer

        target += x
    }

    fun pop(): Int {
        val pop = onlyOne.pop()

        while (buffer.size > 1) {
            onlyOne += buffer.pop()
        }

        buffer = onlyOne.also { onlyOne = buffer }
        buffer.reverse()

        return pop
    }

    fun peek(): Int {
        return onlyOne.peek()
    }

    fun empty(): Boolean {
        return onlyOne.isEmpty()
    }

}
