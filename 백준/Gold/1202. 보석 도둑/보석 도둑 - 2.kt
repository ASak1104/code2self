import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    val gems = MutableList(n) {
        StringTokenizer(readLine()).run { nextToken().toInt() to nextToken().toInt() }
    }
    gems.sortByDescending { it.second }

    val bags = TreeMap<Int, Int>()
    repeat(k) {
        val bag = readLine().toInt()
        bags[bag] = (bags[bag] ?: 0) + 1
    }
    close()

    var res = 0L

    for (gem in gems) {
        if (bags.lastKey() < gem.first) continue

        res += gem.second
        val (key, value) = bags.ceilingEntry(gem.first)

        when (value) {
            1 -> bags.remove(key)
            else -> bags[key] = value - 1
        }

        if (bags.size == 0) break
    }
    print(res)
}