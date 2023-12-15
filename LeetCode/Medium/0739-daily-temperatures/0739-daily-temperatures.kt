class Solution {

    val stack = ArrayDeque<Int>(1e5.toInt())

    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val res = IntArray(temperatures.size)

        for ((idx, temperature) in temperatures.withIndex()) {
            while (stack.isNotEmpty() && temperatures[stack.last()] < temperature) {
                val past = stack.removeLast()

                res[past] = idx - past
            }

            stack += idx
        }

        return res
    }
}
