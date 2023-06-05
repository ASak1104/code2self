import java.util.*
import kotlin.collections.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val gems = MutableList(n) {
        StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    }
    val bags = ArrayDeque<Int>(k)
    repeat(k) { bags.add(readLine().toInt()) }
    close()

    gems.sortByDescending { it.second }
    bags.sort()

    var res = 0L

    for (gem in gems) {
        if (bags[bags.lastIndex] < gem.first) continue

        bags.removeAt(bisect(bags, gem.first))
        res += gem.second

        if (bags.size == 0) break
    }
    print(res)
}

fun bisect(bags: List<Int>, target: Int): Int {
    var s = 0
    var e = bags.lastIndex

    while (s < e) {
        val m = (s + e) ushr 1
        val v = bags[m]

        when {
            v == target -> return m
            v < target -> s = m + 1
            else -> e = m
        }
    }
    return e
}