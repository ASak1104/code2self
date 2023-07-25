import java.io.StreamTokenizer

val st = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(st) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()
    val m = readInt()

    val states = ArrayDeque<Int>(n)
    val votes = IntArray(101)

    repeat(m) {
        val vote = readInt()

        votes[vote]++

        if (votes[vote] > 1) {
            return@repeat
        }

        if (states.size < n) {
            states.addLast(vote)
            return@repeat
        }

        val min = states.minBy { votes[it] }

        votes[min] = 0
        states.remove(min)
        states.addLast(vote)
    }

    states.sort()

    with(System.out.bufferedWriter()) {
        states.joinTo(this, " ")
        flush()
        close()
    }
}