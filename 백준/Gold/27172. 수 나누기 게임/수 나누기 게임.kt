import java.io.StreamTokenizer

private val st = StreamTokenizer(System.`in`.bufferedReader())

private fun readInt(): Int {
    st.nextToken()
    return st.nval.toInt()
}

fun main() {
    val n = readInt()
    val players = IntArray(n) { readInt() }
    val playerSet = players.toSet()

    val maxValue = playerSet.max()
    val points = IntArray(maxValue + 1)

    for (user in players) {
        for (k in user * 2..maxValue step user) {
            if (k in playerSet) {
                points[user]++
                points[k] -= 1
            }
        }
    }

    with(System.out.bufferedWriter()) {
        players.forEach { append("${points[it]} ") }
        flush()
        close()
    }
}