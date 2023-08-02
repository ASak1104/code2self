class Solution {    
    fun solution(topping: IntArray): Int {
        val n = topping.size
        val LR = IntArray(n)
        val RL = IntArray(n)
        val LRSet = HashSet<Int>(n)
        val RLSet = HashSet<Int>(n)
        
        for (i in 0 until n) {
            LRSet += topping[i]
            
            LR[i] = LRSet.size
        }
        
        for (i in n - 1 downTo 0) {
            RL[i] = RLSet.size
            
            RLSet += topping[i]
        }
        
        var res = 0
        
        for (i in 0 until n) {
            if (LR[i] == RL[i]) res++
        }
        
        return res
    }
}