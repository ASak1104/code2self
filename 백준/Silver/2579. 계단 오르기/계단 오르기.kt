import java.io.StreamTokenizer

val sttk = StreamTokenizer(System.`in`.bufferedReader())

fun readInt() = with(sttk) {
    nextToken()
    nval.toInt()
}

fun main() {
    val n = readInt()

    if (n == 1) {
        return print(readInt())
    }

    val step = IntArray(n + 1)

    for (i in 1..n) {
        step[i] = readInt()
    }

    val dp = IntArray(n + 1)

    dp[1] = step[1]
    dp[2] = step[1] + step[2]

    for (i in 3..n) {
        dp[i] = maxOf(dp[i - 2], dp[i - 3] + step[i - 1]) + step[i]
    }

    print(dp[n])
}