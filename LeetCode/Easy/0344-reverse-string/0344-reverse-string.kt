class Solution {

    fun reverseString(s: CharArray): Unit {
        val mid = s.size ushr 1

        for (i in 0 until mid) {
            swap(s, i, s.lastIndex - i)
        }
    }
    
    inline fun swap(s: CharArray, left: Int, right: Int) {
        val temp = s[left]
        
        s[left] = s[right]
        s[right] = temp
    }
}
