import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val govArr = readLine().split(' ').map { it.toInt() }.toIntArray()
    val budget = readLine().toInt()
    var right = govArr.max()
    if (budget >= govArr.sum()) {
        return print(right)
    }
    var left = 1
    while (left <= right) {
        val mid = (left + right) shr 1
        val sum = govArr.sumOf { min(it, mid) }
        if (sum == budget) {
            return print(mid)
        }
        if (sum > budget) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    print(right)
}