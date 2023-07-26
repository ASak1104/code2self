import kotlin.math.abs;

class Solution {
    val edges = Array(101) { ArrayList<Int>() }
    val visit = BooleanArray(101)
    var res = 100
    
    fun solution(n: Int, wires: Array<IntArray>): Int {
        for ((u, v) in wires) {
            edges[u] += v
            edges[v] += u
        }
        
        for ((u, v) in wires) {
            visit.fill(false)
            visit[u] = true
            visit[v] = true
            
            res = minOf(res, abs(travel(u) - travel(v)))
        }
        
        return res
    }
    
    fun travel(u: Int): Int {
        visit[u] = true
        
        var count = 1
        
        for (v in edges[u]) {
            if (!visit[v]) {
                count += travel(v)
            }
        }
        
        return count
    }
}