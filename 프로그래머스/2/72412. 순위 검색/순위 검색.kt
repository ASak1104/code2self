class Solution {

    private val keyToScore = mutableMapOf<Set<String>, MutableList<Int>>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = IntArray(query.size)

        init(info)

        for ((i, q) in query.withIndex()) {
            val keys = q.replace("and ", "")
                .split(' ')
                .filter { it != "-" }
                .toMutableList()
            val score = keys.removeLast().toInt()

            answer[i] = filter(keys, score)
        }

        return answer
    }

    private fun init(info: Array<String>) {
        for (keys in info) {
            val (lang, part, career, food, score) = keys.split(' ')
            val key = setOf(lang, part, career, food)

            keyToScore.getOrPut(key) { mutableListOf() } += score.toInt()
        }

        keyToScore.values.forEach { it.sort() }
    }

    private fun filter(keys: List<String>, score: Int): Int {
        var count = 0

        for ((keySet, scores) in keyToScore) {
            if (!keySet.containsAll(keys)) {
                continue
            }

            count += countGreaterOrEqual(scores, score)
        }

        return count
    }

    private fun countGreaterOrEqual(list: List<Int>, target: Int): Int {
        var left = 0
        var right = list.lastIndex
        var count = 0

        while (left <= right) {
            val mid = (left + right) ushr 1

            if (list[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
                count = list.size - mid
            }
        }

        return count
    }
}
