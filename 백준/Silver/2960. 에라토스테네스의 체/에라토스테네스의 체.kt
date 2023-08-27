import java.util.*

val primes = BooleanArray(1001) { true }
var N = 0
var K = 0
var time = 0
var res = 0

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())

    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    outer@ for (p in 2..N) {
        if (!primes[p]) continue

        for (np in p..N step p) {
            if (!primes[np]) continue

            primes[np] = false

            if (++time == K) {
                res = np
                break@outer
            }
        }
    }

    println(res)
    br.close()
}