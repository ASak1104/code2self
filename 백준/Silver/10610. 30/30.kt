fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toMutableList()

    br.close()

    var tenCondition = false
    var sum = 0

    for (c in n) {
        if (!tenCondition && c == '0') {
            tenCondition = true
        }

        sum += c - '0'
    }

    if (tenCondition && sum % 3 == 0) {
        n.sortDescending()
        println(n.joinToString(""))
    } else {
        println(-1)
    }
}