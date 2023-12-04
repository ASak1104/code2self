class Solution {

    val words = HashMap<String, ArrayList<String>>()

    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        for (word in strs) {
            val key = word.toList()
                .sorted()
                .joinToString("")

            if (key !in words) {
                words[key] = arrayListOf()
            }
            
            words[key]!! += word
        }

        return words.values.toList()
    }
}
