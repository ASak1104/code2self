class Solution {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = IntArray(commands.size)
        for ((i, command) in commands.withIndex()) {
            val s = array.slice(command[0] - 1 until command[1])
            answer[i] = s.sorted()[command[2] - 1]
        }
        return answer
    }
}