import kotlin.streams.toList

class Solution {

    val answers: ArrayList<List<Int>> = ArrayList()

    lateinit var candidates: IntArray

    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        this.candidates = candidates

        travel(0, target, ArrayList())

        return answers
    }

    fun travel(start: Int, target: Int, combinations: ArrayList<Int>) {
        if (target == 0) {
            answers += combinations.stream().toList()
        }

        if (target <= 0 || start >= candidates.size) {
            return
        }

        combinations += candidates[start]
        travel(start, target - candidates[start], combinations)

        combinations.removeLast()
        travel(start + 1, target, combinations)
    }
}
