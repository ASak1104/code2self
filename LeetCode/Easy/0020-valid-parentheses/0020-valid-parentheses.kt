const val MAX_STACK_SIZE = 1e4.toInt() / 2

class Solution {

    val brackets = mapOf('(' to ')', '{' to '}', '[' to ']')
    val stack = ArrayDeque<Char>(MAX_STACK_SIZE)

    fun isValid(s: String): Boolean {
        for (bracket in s) {
            val localValid = determine(bracket)

            if (!localValid) return false
        }

        return stack.isEmpty()
    }

    fun determine(bracket: Char): Boolean {
        if (bracket in brackets) {
            stack += brackets[bracket]!!

            return true
        }

        return stack.isNotEmpty() && bracket == stack.removeLast()
    }
}
