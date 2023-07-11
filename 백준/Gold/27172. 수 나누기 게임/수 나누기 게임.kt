import java.io.StreamTokenizer

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int {
    st.nextToken()
    return st.nval.toInt()
}

fun main() {
    val n = readInt()
    val users = IntArray(n) { readInt() }
    val maxUser = users.max()

    val points = IntArray(maxUser + 1)
    val isUser = BooleanArray(maxUser + 1)

    for (user in users) {
        isUser[user] = true
    }

    for (user in users) {
        for (k in user * 2..maxUser step user) {
            if (isUser[k]) {
                points[user]++
                points[k] -= 1
            }
        }
    }

    with(System.out.bufferedWriter()) {
        users.forEach { append("${points[it]} ") }
        flush()
        close()
    }
}