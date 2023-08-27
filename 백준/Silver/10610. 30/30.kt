fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine()

    br.close()

    val counts = IntArray(10)
    var tenCondition = false
    var sum = 0

    for (c in n) {
        val num = c - '0'

        if (!tenCondition && num == 0) {
            tenCondition = true
        }

        sum += num
        counts[num]++
    }

    if (tenCondition && sum % 3 == 0) {
        val sb = StringBuilder()

        for (num in 9 downTo 0) {
            repeat(counts[num]) {
                sb.append(num)
            }
        }

        println(sb)
    } else {
        println(-1)
    }
}