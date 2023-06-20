val firstOperators = arrayOf("*", "/")
val secondOperators = arrayOf("+", "-")

fun main() = with(System.`in`.bufferedReader()) {
    val expression = readLine().map { it.toString() }
    close()

    val res = convert(expression)

    with(System.out.bufferedWriter()) {
        append(res).flush()
        close()
    }
}

fun convert(list: List<String>): String {
    val exp = removeBracket(list)
    val deque = ArrayDeque<String>(exp.size ushr 1)
    var i = 0

    while (i < exp.size) {
        val s = exp[i++]

        if (s in firstOperators) {
            val sb = StringBuilder()

            sb.append(deque.removeLast())
            sb.append(exp[i])
            sb.append(s)

            deque.addLast(sb.toString())
            i += 1
            continue
        }
        deque.addLast(s)
    }

    val res = StringBuilder()

    while (deque.isNotEmpty()) {
        val pop = deque.removeFirst()

        if (pop in secondOperators) {
            res.append(deque.removeFirst())
        }
        res.append(pop)
    }

    return res.toString()
}

fun removeBracket(exp: List<String>): List<String> {
    val stack = ArrayList<String>(exp.size)
    var i = 0

    while (i < exp.size) {
        val s = exp[i++]

        if (s == "(") {
            var cnt = 1
            var j = i

            while (cnt != 0) {
                if (exp[j] == "(") cnt++
                else if (exp[j] == ")") cnt--
                j++
            }
            stack.add(convert(exp.subList(i, j - 1)))
            i = j
            continue
        }
        stack.add(s)
    }

    return stack
}