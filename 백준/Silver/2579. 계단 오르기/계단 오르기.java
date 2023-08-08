import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[][] dp;
    static int[] seq;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        seq = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[n + 1][2];
        dp[1][0] = seq[1];
        dp[1][1] = seq[1];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = seq[i] + Math.max(dp[i - 2][0], dp[i - 2][1]);
            dp[i][1] = seq[i] + dp[i - 1][0];
        }

        System.out.println(Math.max(dp[n][0], dp[n][1]));
        br.close();
    }
}