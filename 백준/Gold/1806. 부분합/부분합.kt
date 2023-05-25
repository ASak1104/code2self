import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(' ').map(String::toInt)
    val seq = readLine().split(' ').map(String::toInt).toIntArray()
    
    for (i in 1 until n) {
        seq[i] += seq[i - 1]
    }
    var result = 100_000
    var left = 0
    var right = 0
    
    while (right < n && seq[right] < s) right++
    
    while (right < n) {
        while (seq[right] - seq[left] >= s) left++
        result = min(result, right - left + 1)
        right++
    }
    if (result == 100_000) result = 0
    print(result)
}