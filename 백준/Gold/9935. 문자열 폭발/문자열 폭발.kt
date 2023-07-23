fun main() = with(System.`in`.bufferedReader()) {
    val string = readLine()
    val exp = readLine()
    val stack = ArrayDeque<Char>(string.length)

    fun check() {
        if (stack.size < exp.length) return

        val offset = stack.size - exp.length

        for (i in exp.indices) {
            if (stack[offset + i] != exp[i]) return
        }

        repeat(exp.length) { stack.removeLast() }
    }

    for (c in string) {
        stack.addLast(c)
        check()
    }

    with(System.out.bufferedWriter()) {
        if (stack.isEmpty()) append("FRULA")

        stack.forEach(::append)

        flush()
        close()
    }
}