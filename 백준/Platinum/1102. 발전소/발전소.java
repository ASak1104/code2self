import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static String state;
    static int[][] costs;
    static int[] dp;
    static int n, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        costs = new int[n][n];

        for (int r = 0; r < n; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int c = 0; c < n; c++) {
                costs[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        state = br.readLine();
        p = Integer.parseInt(br.readLine());

        int visit = 0;
        int count = 0;

        for (int u = 0; u < n; u++) {
            if (state.charAt(u) == 'Y') {
                visit |= 1 << u;
                count++;
            }
        }

        int res = -1;

        if (p == 0 || visit != 0) {
            dp = new int[(1 << n)];
            Arrays.fill(dp, -1);

            res = travel(visit, count);
        }

        System.out.println(res);
        br.close();
    }

    static int travel(int visit, int count) {
        if (count >= p) {
            return 0;
        }

        if (dp[visit] != -1) {
            return dp[visit];
        }

        int ret = Integer.MAX_VALUE;

        for (int v = 0; v < n; v++) {
            int mask = 1 << v;

            if ((visit & mask) == mask) continue;

            int minCost = Integer.MAX_VALUE;

            for (int u = 0; u < n; u++) {
                if ((visit & (1 << u)) == 0) continue;

                minCost = Math.min(minCost, costs[u][v]);
            }

            ret = Math.min(ret, minCost + travel(visit | mask, count + 1));
        }

        dp[visit] = ret;

        return ret;
    }
}