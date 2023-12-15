class MyStack() {

    var onlyOne = ArrayDeque<Int>(100)
    var buffer = ArrayDeque<Int>(100)

    fun push(x: Int) {
        if (onlyOne.isNotEmpty()) {
            buffer += onlyOne.removeFirst()
        }

        onlyOne += x
    }

    fun pop(): Int {
        val pop = onlyOne.removeFirst()

        while (buffer.size > 1) {
            onlyOne += buffer.removeFirst()
        }

        onlyOne = buffer.also { buffer = onlyOne }

        return pop
    }

    fun top(): Int {
        return onlyOne.first()
    }

    fun empty(): Boolean {
        return onlyOne.isEmpty()
    }

}
