import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int[][] dp;
    static int[] cards;
    static int t, n, p;
    static boolean isLast, turn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        StringTokenizer st;

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            dp = new int[n][n];
            cards = new int[n];

            isLast = (n & 1) == 1;

            for (int i = 0; i < n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());

                if (isLast) {
                    dp[i][i] = cards[i];
                }
            }

            turn = !isLast;
            p = 0;

            for (int k = 1; k < n; k++) {
                for (int s = 0; s < n - k; s++) {
                    int e = s + k;

                    if (turn) {
                        dp[s][e] = Math.max(dp[s + 1][e] + cards[s], dp[s][e - 1] + cards[e]);
                    } else {
                        dp[s][e] = Math.min(dp[s + 1][e], dp[s][e - 1]);
                    }
                }

                turn = !turn;
            }

            bw.append(String.valueOf(dp[0][n - 1])).append("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}