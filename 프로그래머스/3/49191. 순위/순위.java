import java.util.*;

class Solution {
    
    private List<Integer>[] winEdges;
    private List<Integer>[] defeatEdges;
    
    public int solution(int n, int[][] results) {
        winEdges = new List[n + 1];
        defeatEdges = new List[n + 1];
        
        for (int u = 1; u <= n; u++) {
            winEdges[u] = new ArrayList<>();
            defeatEdges[u] = new ArrayList<>();
        }
        
        for (int[] edge : results) {
            int u = edge[0];
            int v = edge[1];
            
            winEdges[u].add(v);
            defeatEdges[v].add(u);
        }
        
        int answer = 0;
        
        for (int u = 1; u <= n; u++) {
            int win = travel(winEdges, new boolean[n + 1], u);
            int defeat = travel(defeatEdges, new boolean[n + 1], u);
            
            if (win + defeat == n + 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private int travel(List<Integer>[] edges, boolean[] visit, int u) {
        int cnt = 1;
        
        visit[u] = true;
        
        for (int v : edges[u]) {
            if (visit[v]) {
                continue;
            }
            
            cnt += travel(edges, visit, v);
        }
        
        return cnt;
    }
}