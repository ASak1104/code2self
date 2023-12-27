class Solution {

    val isJewels: IntArray = IntArray(58)
    
    fun numJewelsInStones(jewels: String, stones: String): Int {
        var count = 0
        
        for (jewel in jewels) {
            isJewels[jewel - 'A'] = 1
        }

        for (stone in stones) {
            count += isJewels[stone - 'A']
        }

        return count
    }
}
