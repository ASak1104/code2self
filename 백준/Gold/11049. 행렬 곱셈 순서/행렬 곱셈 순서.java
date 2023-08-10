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

        for (int k = 1; k < n; k++) {
            for (int s = 0; s < n - k; s++) {
                int min = Integer.MAX_VALUE;
                int e = s + k;

                for (int j = s; j < e; j++) {
                    int sub = matrices[s][0] * matrices[j][1] * matrices[e][1] + dp[s][j] + dp[j + 1][e];

                    min = Math.min(min, sub);
                }

                dp[s][e] = min;
            }
        }

        System.out.println(dp[0][n - 1]);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}