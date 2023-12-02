class Solution {

    val regex = Regex("[ !?',;.]")
    val words = HashMap<String, Int>()

    lateinit var bans: Set<String>

    fun mostCommonWord(paragraph: String, banned: Array<String>): String {
        bans = banned.toSet()

        val target = paragraph
            .lowercase()
            .split(regex)

        for (word in target) {
            if (word.isBlank()) continue

            words[word] = (words[word] ?: 0) + 1
        }

        return words
            .filter { entry -> entry.key !in bans }
            .maxBy { entry -> entry.value }
            .key
    }
}
