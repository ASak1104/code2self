import kotlin.math.sqrt

fun main() {
    val n = readln().toInt()
    val nums = BooleanArray(n + 1) { true }
    val sqrtN = sqrt(n.toDouble()).toInt()

    var k = 2
    while (k <= sqrtN) {
        var nonPrime = k * k
        while (nonPrime <= n) {
            nums[nonPrime] = false
            nonPrime += k
        }
        do { k++ } while (!nums[k] && k < sqrtN)
    }

    val primes = ArrayList<Int>(283_146)
    for (i in 2..n) {
        if (nums[i]) primes.add(i)
    }

    var res = 0
    for (i in primes.indices) {
        var diff = n - primes[i]
        var j = i + 1
        
        while (diff > 0 && j < primes.size) {
            diff -= primes[j++]
        }
        if (diff == 0) res++
    }
    print(res)
}