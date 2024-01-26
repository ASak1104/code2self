import java.util.*

const val MAX_LIGHT = 1e6.toInt()

val br = System.`in`.bufferedReader()
val sb = StringBuilder()

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

    println(sb)
    br.close()
}

fun run() {
    val lights = IntArray(MAX_LIGHT + 1)
    val pq = PriorityQueue<Int>(reverseOrder())

    for (i in 0 until m * 2 - 1) {
        lights[neonsigns[i]]++
        pq += neonsigns[i]
    }

    sb.append(pq.peek()).append(' ')

    for (mid in m..n - m) {
        lights[neonsigns[mid - m]]--
        lights[neonsigns[mid + m - 1]]++
        pq += neonsigns[mid + m - 1]

        while (lights[pq.peek()] == 0) {
            pq.poll()
        }

        sb.append(pq.peek()).append(' ')
    }
}
