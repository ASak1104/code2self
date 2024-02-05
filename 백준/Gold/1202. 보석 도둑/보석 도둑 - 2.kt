import java.util.*
import kotlin.collections.ArrayDeque

val br = System.`in`.bufferedReader()

var n = 0
var k = 0

lateinit var jewels: Array<Jewel>
lateinit var bags: ArrayDeque<Int>

fun main() {
    with(StringTokenizer(br.readLine())) {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }

    jewels = Array(n) {
        StringTokenizer(br.readLine()).run {
            Jewel(nextToken().toInt(), nextToken().toInt())
        }
    }

    bags = ArrayDeque(k)

    repeat(k) { bags += br.readLine().toInt() }

    grandTheftAuto()

    br.close()
}

fun grandTheftAuto() {
    var bigMoney = 0L

    jewels.sortByDescending(Jewel::price)
    bags.sort()

    for (jewel in jewels) {
        if (bags.isEmpty()) {
            break
        }

        val idx = findAtLeast(jewel.weight)

        if (idx >= 0) {
            bigMoney += jewel.price
            bags.removeAt(idx)
        }
    }

    println(bigMoney)
}

fun findAtLeast(target: Int): Int {
    var left = 0
    var right = bags.lastIndex
    var atLeast = -1

    while (left <= right) {
        val mid = (left + right) ushr 1

        if (bags[mid] == target) {
            return mid
        }

        if (bags[mid] < target) {
            left = mid + 1
        } else {
            right = mid - 1
            atLeast = mid
        }
    }

    return atLeast
}

data class Jewel(val weight: Int, val price: Int)
