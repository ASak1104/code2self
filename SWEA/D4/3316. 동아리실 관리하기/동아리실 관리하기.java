import java.io.*;
import java.util.Arrays;

public class Solution {

    static final int MOD = 1_000_000_007;
    static int[][] memo = new int[10001][16];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String s = br.readLine();

            for (int i = 0; i < s.length(); i++) {
                Arrays.fill(memo[i], 0);
            }

            int mask = (1 << (s.charAt(0) - 'A')) | 1;

            for (int j = 1; j < 16; j++) {
                if ((j & mask) == mask) {
                    memo[0][j] = 1;
                }
            }

            for (int i = 1; i < s.length(); i++) {
                mask = 1 << (s.charAt(i) - 'A');

                for (int j = 1; j < 16; j++) {
                    if ((j & mask) == 0) continue;

                    for (int k = 1; k < 16; k++) {
                        if ((j & k) > 0) {
                            memo[i][j] = (memo[i][j] + memo[i - 1][k]) % MOD;
                        }
                    }
                }
            }

            int res = 0;

            for (int j = 1; j < 16; j++) {
                res = (res + memo[s.length() - 1][j]) % MOD;
            }

            bw.append(String.format("#%d %d\n", tc, res));
        }

        bw.flush();
        bw.close();
    }
}