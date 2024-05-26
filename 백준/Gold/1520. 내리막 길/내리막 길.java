import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static StringTokenizer st;
    private static int[][] map, dp;
    private static int n, m;
    
    public static void main(String args[]) throws IOException {
        init();

        System.out.println(travel(n, m));
    }
    
    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 2][m + 2];
        dp = new int[n + 2][m + 2];
        
        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());
            
            for (int c = 1; c <= m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                dp[r][c] = -1;
            }
        }
        
        dp[1][1] = 1;
    }
    
    private static int travel(int r, int c) {
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        
        dp[r][c] = 0;
        
        update(r, c, r + 1, c);
        update(r, c, r - 1, c);
        update(r, c, r, c - 1);
        update(r, c, r, c + 1);
        
        return dp[r][c];
    }
    
    private static void update(int r, int c, int tr, int tc) {
        if (map[r][c] < map[tr][tc]) {
            dp[r][c] += travel(tr, tc);
        }
    }
}
