class Solution {

    fun removeDuplicateLetters(s: String): String {
        if (s.length <= 1) {
            return s
        }

        val mask = getMask(s, 0)

        var i = 0
        var bit = 1

        while (bit <= mask) {
            val c = 'a' + i

            if (bit and mask == bit) {
                val start = s.indexOf(c)

                if (mask == getMask(s, start)) {
                    val next = s.substring(start + 1)
                        .replace(c.toString(), "")

                    return c + removeDuplicateLetters(next)
                }
            }

            bit = 1 shl ++i
        }

        return ""
    }

    fun getMask(s: String, start: Int): Int {
        var ret = 0

        for (i in start until s.length) {
            ret = ret or (1 shl (s[i] - 'a'))
        }

        return ret
    }
}
