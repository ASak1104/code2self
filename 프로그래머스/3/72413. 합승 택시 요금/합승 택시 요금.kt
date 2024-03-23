class Solution {
    
    val INF = 1e8.toInt()
    val edges = Array(201) { IntArray(201) { INF } }
    
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        for (u in 1..n) {
            edges[u][u] = 0
        }
        
        for ((u, v, w) in fares) {
            edges[u][v] = w
            edges[v][u] = w
        }
        
        for (k in 1..n) {
            for (u in 1..n) {
                for (v in 1..n) {
                    edges[u][v] = minOf(edges[u][v], edges[u][k] + edges[k][v])
                }
            }
        }
        
        var answer = INF
        
        for (k in 1..n) {
            answer = minOf(answer, edges[s][k] + edges[k][a] + edges[k][b])
        }
        
        return answer
    }
}
