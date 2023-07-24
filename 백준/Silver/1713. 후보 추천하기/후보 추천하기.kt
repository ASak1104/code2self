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

        if (vote in states) {
            return@repeat
        }

        if (states.size < n) {
            states.addLast(vote)
            return@repeat
        }

        val minIdx = states.minBy { votes[it] }

        votes[minIdx] = 0
        states.remove(minIdx)
        states.addLast(vote)
    }

    states.sort()

    with(System.out.bufferedWriter()) {
        states.joinTo(this, " ")
        flush()
        close()
    }
}