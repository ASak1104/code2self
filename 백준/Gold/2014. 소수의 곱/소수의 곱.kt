import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())

    val k = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())

    val primes = IntArray(k) { st.nextToken().toInt() }
    val visit = primes.toHashSet()
    val pq = PriorityQueue(visit)

    var max = primes.last()

    for (count in 1 until n) {
        val num = pq.poll()

        for (p in primes) {
            if (1L * p * num >= Int.MAX_VALUE) continue

            val next = p * num

            if (next in visit) continue

            if (pq.size + count >= n && next > max) break

            pq.offer(next)
            visit.add(next)
            max = maxOf(max, next)
        }
    }

    println(pq.peek())
    br.close()
}