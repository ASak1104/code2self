class Solution {
    var res = 1e8.toInt()
    
    fun solution(storey: Int): Int {
        
        elevator(storey, 10, 0)
        
        return res
    }
    
    fun elevator(floor: Int, btn: Int, cnt: Int) {
        if (cnt >= res) return
        
        if (floor == 0) {
            res = minOf(res, cnt)
            
            return
        }
        
        var k = btn
        
        while (floor % k == 0) k *= 10
        
        val r = floor % k
        val r0 = r / (k / 10)
        
        elevator(floor - r, k, cnt + r0)
        elevator(floor - r + k, k, cnt + 10 - r0)
    }
}