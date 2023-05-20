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
    var mid = (left + right) shr 1
    while (left < right) {
        var sum = 0
        for (gov in govArr) {
            sum += min(gov, mid)
        }
        if (sum == budget) {
            return print(mid)
        }
        if (sum > budget) {
            right = mid
        } else {
            left = mid + 1
        }
        mid = (left + right) shr 1
    }
    print(right - 1)
}