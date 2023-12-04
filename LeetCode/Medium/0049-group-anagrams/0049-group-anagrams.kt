class Solution {

    val words = HashMap<String, ArrayList<String>>()

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        for (word in strs) {
            val key = generateKey(word)

            if (key !in words) {
                words[key] = arrayListOf()
            }

            words[key]!! += word
        }

        return words.values.toList()
    }

    inline fun generateKey(word: String): String {
        val freq = CharArray(26)

        for (c in word) {
            freq[c - 'a']++
        }

        return freq.concatToString()
    }
}
