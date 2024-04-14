class Solution {
    
    private static final int MOD = 1_000_000_007;
    
    private int[][] dp = new int[101][101];
    private boolean[][] isPuddles = new boolean[101][101];
    
    public int solution(int m, int n, int[][] puddles) {
        for (int[] puddle : puddles) {
            isPuddles[puddle[1]][puddle[0]] = true;
        }
        
        for (int r = 2; r <= n; r++) {
            if (isPuddles[r][1]) {
                break;
            }
            
            dp[r][1] = 1;
        }
        
        for (int c = 2; c <= m; c++) {
            if (isPuddles[1][c]) {
                break;
            }
            
            dp[1][c] = 1;
        }
        
        for (int r = 2; r <= n; r++) {
            for (int c = 2; c <= m; c++) {
                if (isPuddles[r][c]) {
                    continue;
                }
                
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % MOD;
            }
        }
        
        return dp[n][m];
    }
}