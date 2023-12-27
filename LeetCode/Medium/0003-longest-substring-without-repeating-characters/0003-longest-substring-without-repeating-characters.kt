class Solution {

    val visit: MutableSet<Char> = mutableSetOf()
    val deque: ArrayDeque<Char> = ArrayDeque()

    var res = 0

    fun lengthOfLongestSubstring(s: String): Int {
        for (c in s) {
            while (c in visit) {
                visit -= deque.removeFirst()
            }

            deque.addLast(c)
            visit += c

            res = maxOf(res, deque.size)
        }

        return res
    }
}
