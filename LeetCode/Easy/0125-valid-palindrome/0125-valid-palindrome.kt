class Solution {

    private val regex: Regex = Regex("[^a-z0-9]")
    
    fun isPalindrome(s: String): Boolean {
        val preprocessed: String = preprocess(s)
        val length: Int = preprocessed.length
        val mid: Int = length ushr 1

        for (i in 0 until mid) {
            if (preprocessed[i] != preprocessed[length - i - 1]) {
                return false
            }
        }

        return true
    }

    private fun preprocess(s: String): String {
        return s.lowercase()
            .replace(regex, "")
    }
}
