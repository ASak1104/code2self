import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Main {

    private static final StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    private static int readInt() throws IOException {
        sttk.nextToken();

        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int R = readInt();
        int C = readInt();
        int N = readInt();
        int[][] roads = new int[R + 1][C + 1];
        int[][] dp = new int[R + 2][C + 2];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                roads[r][c] = readInt();
            }
        }

        dp[1][1] = N - 1;

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                int v = dp[r][c] >>> 1;

                dp[r][c + 1] += v;
                dp[r + 1][c] += v;

                if (roads[r][c] == 1) {
                    dp[r][c + 1] += dp[r][c] & 1;
                } else {
                    dp[r + 1][c] += dp[r][c] & 1;
                }
            }
        }

        int r = 1;
        int c = 1;

        while (r <= R && c <= C) {
            roads[r][c] += (dp[r][c] & 1);

            if ((roads[r][c] & 1) == 1) {
                c++;
            } else {
                r++;
            }
        }

        System.out.printf("%d %d", r, c);
    }
}