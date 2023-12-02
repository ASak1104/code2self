class Solution {
    
    val regex = Regex("[^a-z^0-9]")

    fun isPalindrome(s: String): Boolean {
        val target = s.lowercase()
            .replace(regex, "")

        return target == target.reversed()
    }
}
