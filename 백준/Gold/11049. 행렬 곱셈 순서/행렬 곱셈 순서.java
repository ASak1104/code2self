import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static int[][] matrices, dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        matrices = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            matrices[i][0] = nextInt();
            matrices[i][1] = nextInt();
        }

        dp = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            dp[1][i] = matrices[i][0] * matrices[i][1] * matrices[i + 1][1];
        }

        for (int k = 2; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                int min = Integer.MAX_VALUE;

                for (int j = i + 1; j < i + k; j++) {
                    int left = matrices[i][0] * matrices[j][1] * matrices[i + k][1];
                    int right = matrices[i][0] * matrices[j][0] * matrices[i + k][1];

                    left += dp[j - i][i] + dp[i + k - j - 1][j + 1];
                    right += dp[j - i - 1][i] + dp[i + k - j][j];

                    min = Math.min(min, Math.min(left, right));
                }

                dp[k][i] = min;
            }
        }

        System.out.println(dp[n - 1][0]);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}