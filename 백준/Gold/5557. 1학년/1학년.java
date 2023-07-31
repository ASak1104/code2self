import java.io.*;

class Main {

    private static final StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    private static int readInt() throws IOException {
        sttk.nextToken();

        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int[] seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = readInt();
        }

        long[][] dp = new long[n - 1][21];

        dp[0][seq[0]] = 1;

        for (int i = 0; i < n - 2; i++) {
            for (int v = 0; v <= 20; v++) {
                if (dp[i][v] == 0) continue;

                int pv = v + seq[i + 1];
                int mv = v - seq[i + 1];

                if (0 <= pv && pv <= 20) {
                    dp[i + 1][pv] += dp[i][v];
                }

                if (0 <= mv && mv <= 20) {
                    dp[i + 1][mv] += dp[i][v];
                }
            }
        }

        System.out.println(dp[n - 2][seq[n - 1]]);
    }
}