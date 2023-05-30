import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val testCase = br.readLine().toInt()

    repeat(testCase) {
        val n = br.readLine().toInt()
        val islands = MutableList(n) {
            val line = StringTokenizer(br.readLine())
            Island(line.nextToken().toInt(), line.nextToken().toInt())
        }

        islands.sortByDescending { it.y }
        var count = 0
        for (i in n - 1 downTo 1) {
            if (islands[i].y == islands[i - 1].y) {
                islands[i].y = count
            } else {
                islands[i].y = count++
            }
        }
        islands[0].y = count
        islands.sortBy { it.x }

        var answer = 0L
        val nw = Northwester(count + 1)
        for (e in islands) {
            answer += nw.query(e.y, count)
            nw.update(e.y)
        }
        bw.write(answer.toString())
        bw.newLine()
    }
    bw.flush()
    bw.close()
    br.close()
}

data class Island(var x: Int, var y: Int)

class Northwester(n: Int) {
    val tree: IntArray
    var capacity = 1

    init {
        while (capacity < n) capacity *= 2
        tree = IntArray(capacity shl 1)
    }

    fun update(y: Int) {
        var node = capacity + y
        while (node > 0) {
            tree[node]++
            node = node ushr 1
        }
    }

    fun query(qs: Int, qe: Int): Int {
        var start = capacity + qs
        var end = capacity + qe
        var ret = 0
        while (start < end) {
            if (start and 1 == 1) ret += tree[start++]
            if (end and 1 == 0) ret += tree[end--]
            start = start ushr 1
            end = end ushr 1
        }
        if (start == end) ret += tree[start]
        return ret
    }
}