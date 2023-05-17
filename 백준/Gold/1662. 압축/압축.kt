class Decompressor(target: String) {
    private val s = target
    private var idx = 0

    fun decompress(): Int {
        var result = 0
        while (idx < s.length) {
            if (s[idx] == ')') {
                return result
            }
            result += if (s[idx] == '(') {
                (s[idx++ - 1].code - 48) * decompress() - 1
            } else {
                1
            }
            idx++
        }
        return result
    }
}

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val dcp = Decompressor(readLine())
    println(dcp.decompress())
}