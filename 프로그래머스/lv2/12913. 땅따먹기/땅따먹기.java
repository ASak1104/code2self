class Solution {
    int[][] land;
    
    int solution(int[][] land) {
        this.land = land;
        
        for (int r = 1; r < land.length; r++) {
            for (int c = 0; c < 4; c++) {
                land[r][c] += maxOfLand(r - 1, c);
            }
        }

        return maxOfLand(land.length - 1, -1);
    }
    
    int maxOfLand(int r, int except) {
        int max = 0;
        
        for (int c = 0; c < 4; c++) {
            if (c != except && max < land[r][c])
                max = land[r][c];
        }
        
        return max;
    }
}