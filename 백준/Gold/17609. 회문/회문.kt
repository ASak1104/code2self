import java.io.BufferedReader

fun main(): Unit {
    val br: BufferedReader = System.`in`.bufferedReader()
    val sb: StringBuilder = StringBuilder()
    val n: Int = br.readLine().toInt()

    repeat(n) {
        val s: String = br.readLine()

        sb.append(solve(s))
            .appendLine()
    }

    println(sb)
    br.close()
}

private fun solve(s: String): Int {
    if (isPalindrome(s, 0, s.lastIndex)) {
        return 0
    }

    if (isSimilarPalindrome(s)) {
        return 1
    }

    return 2
}

private fun isPalindrome(s: String, begin: Int, end: Int): Boolean {
    var left: Int = begin
    var right: Int = end

    while (left < right) {
        if (s[left] != s[right]) {
            return false
        }

        left++
        right--
    }

    return true
}

private fun isSimilarPalindrome(s: String): Boolean {
    var left: Int = 0
    var right: Int = s.lastIndex

    while (left < right) {
        if (s[left] == s[right]) {
            left++
            right--
            continue
        }

        return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)
    }

    return true
}
