fun main() = with(System.`in`.bufferedReader()) {
    val exp = readLine()
    close()

    val stack = ArrayDeque<Char>(exp.length)
    val bw = System.out.bufferedWriter()

    for (c in exp) {
        if (c.isLetter()) {
            bw.append(c)
            continue
        }
        if (c == '(') {
            stack.addLast(c)
            continue
        }
        if (c == ')') {
            while (stack.isNotEmpty() && stack.last() != '(') {
                bw.append(stack.removeLast())
            }
            stack.removeLast()
            continue
        }
        while (stack.isNotEmpty() && value(stack.last()) >= value(c)) {
            bw.append(stack.removeLast())
        }
        stack.addLast(c)
    }

    while (stack.isNotEmpty()) {
        bw.append(stack.removeLast())
    }

    bw.flush()
    bw.close()
}

fun value(c: Char) = when (c) {
    '*', '/' -> 2
    '+', '-' -> 1
    else -> 0
}