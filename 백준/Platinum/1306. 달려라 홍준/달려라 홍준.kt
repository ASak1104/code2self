import java.util.*

const val MAX_LIGHT = 1e6.toInt()

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

var n = 0
var m = 0

lateinit var neonsigns: IntArray

fun main() {
    with(StringTokenizer(br.readLine())) {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    with(StringTokenizer(br.readLine())) {
        neonsigns = IntArray(n) { nextToken().toInt() }
    }

    run()

    bw.flush()
    bw.close()
    br.close()
}

fun run() {
    val lights = IntArray(MAX_LIGHT + 1)
    val pq = PriorityQueue<Int>(reverseOrder())

    for (i in 0 until m * 2 - 1) {
        lights[neonsigns[i]]++
        pq += neonsigns[i]
    }

    bw.append("${pq.peek()} ")

    for (mid in m..n - m) {
        lights[neonsigns[mid - m]]--
        lights[neonsigns[mid + m - 1]]++
        pq += neonsigns[mid + m - 1]

        while (lights[pq.peek()] == 0) {
            pq.poll()
        }

        bw.append("${pq.peek()} ")
    }
}
