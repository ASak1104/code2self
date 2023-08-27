import java.util.*
import kotlin.math.sqrt

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    br.close()

    val P = st.nextToken()
    val K = st.nextToken().toInt()
    val primes = BooleanArray(K) { true }
    val sqrt = sqrt(K.toDouble()).toInt()

    for (p in 2..sqrt) {
        for (np in p * p until K step p) {
            primes[np] = false
        }
    }

    for (p in 2 until K) {
        if (!primes[p]) continue

        var r = 0

        for (c in P) {
            r = ((r * 10) + (c - '0')) % p
        }

        if (r == 0) {
            return println("BAD $p")
        }
    }

    println("GOOD")
}