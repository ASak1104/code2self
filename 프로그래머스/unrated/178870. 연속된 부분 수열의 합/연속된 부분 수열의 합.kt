class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var left = 0
        var right = sequence.lastIndex
        var i = 0
        var sum = 0

        for (j in sequence.indices) {
            if (sequence[j] > k) break

            sum += sequence[j]

            while (sum > k) sum -= sequence[i++]

            if (sum == k && j - i < right - left) {
                left = i
                right = j
            }
        }

        return intArrayOf(left, right)
    }
}