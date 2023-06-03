fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val tests = readLine().split(' ').map(String::toInt)
    close()

    val max = tests.max().toDouble()
    print(tests.sumOf { (it / max) * 100 } / n)
}