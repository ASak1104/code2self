class Solution {

    fun characterReplacement(s: String, k: Int): Int {
        val counts = IntArray(26)

        var maxCount = 0
        var left = 0

        for ((right, c) in s.withIndex()) {
            maxCount = maxOf(maxCount, ++counts[c - 'A'])

            // 현재 size가 maxCount + k 보다 크다면
            // window size를 한 칸 줄이면 됨
            // 이전까지는 조건을 만족했으니까!
            if (right - left + 1 > maxCount + k) {
                counts[s[left++] - 'A']--
            }
        }

        return s.length - left
    }
}
