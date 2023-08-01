import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Main {

    private static final int MOD = 100_000;
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[][][] dp = new int[101][101][4];

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int w = readInt();
        int h = readInt();

        for (int i = 2; i <= w; i++) {
            dp[i][1][0] = 1;
        }

        for (int j = 2; j <= h; j++) {
            dp[1][j][1] = 1;
        }

        dp[2][2][2] = 1;
        dp[2][2][3] = 1;

        for (int i = 2; i <= w; i++) {
            for (int j = 2; j <= h; j++) {
                if (i == 2 && j == 2) continue;

                if (i > 2) {
                    dp[i][j][0] = (dp[i - 2][j][0] + dp[i - 2][j][1] + dp[i - 2][j][2]) % MOD;
                }

                if (j > 2) {
                    dp[i][j][1] = (dp[i][j - 2][0] + dp[i][j - 2][1] + dp[i][j - 2][3]) % MOD;
                }

                dp[i][j][2] = (dp[i - 1][j - 1][1] + dp[i - 1][j - 1][3]) % MOD;
                dp[i][j][3] = (dp[i - 1][j - 1][0] + dp[i - 1][j - 1][2]) % MOD;
            }
        }

        System.out.println((dp[w][h][0] + dp[w][h][1] + dp[w][h][2] + dp[w][h][3]) % MOD);
    }
}