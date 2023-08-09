import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int INF = (int) 1e9;
    static int[][][] dp;
    static int cmd, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new int[st.countTokens()][5][5];

        for (int[][] lefts : dp) {
            for (int[] right : lefts) {
                Arrays.fill(right, INF);
            }
        }

        dp[0][0][0] = 0;
        res = 0;

        int i = 0;

        while (++i < dp.length) {
            cmd = Integer.parseInt(st.nextToken());

            if (cmd == 0) break;

            for (int lf = 0; lf <= 4; lf++) {
                for (int rf = 0; rf <= 4; rf++) {
                    if (dp[i - 1][lf][rf] == INF) continue;

                    dp[i][lf][cmd] = Math.min(dp[i][lf][cmd], dp[i - 1][lf][rf] + dist(rf, cmd));
                    dp[i][cmd][rf] = Math.min(dp[i][cmd][rf], dp[i - 1][lf][rf] + dist(lf, cmd));
                }
            }
        }

        int res = INF;

        for (int[] row : dp[i - 1]) {
            for (int v : row) {
                res = Math.min(res, v);
            }
        }

        System.out.println(res);
        br.close();
    }

    static int dist(int u, int v) {
        if (u == v) return 1;

        if (u == 0) return 2;

        if (Math.abs(u - v) == 2) return 4;

        return 3;
    }
}