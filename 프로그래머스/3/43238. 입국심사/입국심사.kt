class Solution {
    
    private val MAX = 1e18.toLong(); 
    
    fun solution(n: Int, times: IntArray): Long {
        var left = 0L;
        var right = MAX;
        var answer = right;
        
        while (left <= right) {
            val mid = (left + right) ushr 1;
            val cnt = simulate(mid, times);
            
            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer
    }
    
    private fun simulate(k: Long, times: IntArray): Long {
        var cnt = 0L;
        
        for (time: Int in times) {
            cnt += k / time;
        }
        
        return cnt;
    } 
}