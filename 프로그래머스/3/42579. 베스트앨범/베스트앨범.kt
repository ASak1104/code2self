class Solution {

    val counts: MutableMap<String, Int> = mutableMapOf()
    val musics: MutableMap<String, ArrayList<Int>> = mutableMapOf()

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer: ArrayList<Int> = arrayListOf()

        for ((i, genre) in genres.withIndex()) {
            counts[genre] = (counts[genre] ?: 0) + plays[i]

            musics.putIfAbsent(genre, arrayListOf())
            musics[genre]!! += i
        }

        val descGenres: List<String> = counts.keys.sortedByDescending { counts[it] }

        for (genre in descGenres) {
            val innerMusics = musics[genre]!!

            innerMusics.sortByDescending { plays[it] }
            answer += innerMusics[0]

            if (innerMusics.size > 1) {
                answer += innerMusics[1]
            }
        }

        return answer.toIntArray()
    }
}
