class Solution {
    lateinit var dungeons: Array<IntArray>
    var n = 0
    var maxCount = 0
    
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        this.dungeons = dungeons
        n = dungeons.size
        
        for (u in 0 until n) {
            if (k < dungeons[u][0]) continue
            
            travel(k - dungeons[u][1], 1 shl u, 1)
        }
        
        return maxCount
    }
    
    fun travel(k: Int, visit: Int, count: Int) {
        maxCount = maxOf(maxCount, count)
        
        for (v in 0 until n) {
            if (k < dungeons[v][0]) continue
            
            val mask = 1 shl v
            
            if (visit and mask == mask) continue
            
            travel(k - dungeons[v][1], visit or mask, count + 1)
        }
    }
}