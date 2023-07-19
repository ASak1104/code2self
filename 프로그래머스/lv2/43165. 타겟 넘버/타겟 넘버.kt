class Solution {
    lateinit var seq: IntArray
    var target = 0
    var res = 0
    
    fun solution(numbers: IntArray, target: Int): Int {
        seq = numbers
        this.target = target
        
        travel(0, 0)
        
        return res
    }
    
    fun travel(idx: Int, k: Int) {
        if (idx == seq.size) {
            if (k == target) res++
            
            return
        }
        
        travel(idx + 1, k + seq[idx])
        travel(idx + 1, k - seq[idx])
    }
}