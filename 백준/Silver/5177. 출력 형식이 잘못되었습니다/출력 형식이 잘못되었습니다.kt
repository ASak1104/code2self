val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

lateinit var target: String
lateinit var other: String

fun main() {
    val testCase = br.readLine().toInt()

    repeat(testCase) {
        target = br.readLine()
        other = br.readLine()

        bw.append("Data Set ${it + 1}: ${compare()}\n\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

fun compare(): String {
    val target = preprocess(target)
    val other = preprocess(other)

    return if (target == other)
        "equal"
    else
        "not equal"
}

fun preprocess(s: String) = s
    .trim()
    .lowercase()
    .replace(";", ",")
    .replace(Regex("[\\[{]"), "(")
    .replace(Regex("[]}]"), ")")
    .replace(Regex("\\s{2,}"), " ")
    .replace(Regex("\\s*[().,:]\\s*")) { it.value.trim() }
