class Solution {

    var start = 0
    var end = 0

    fun longestPalindrome(s: String): String {
        for (i in 0 until s.lastIndex) {
            findPalindrome(s, i, i + 1)
            findPalindrome(s, i - 1, i + 1)
        }

        return s.substring(start..end)
    }

    fun findPalindrome(s: String, leftStart: Int, rightStart: Int) {
        var left = leftStart
        var right = rightStart

        while (left in s.indices && right in s.indices && s[left] == s[right]) {
            left--
            right++
        }

        left++
        right--

        if (end - start < right - left) {
            start = left
            end = right
        }
    }
}
