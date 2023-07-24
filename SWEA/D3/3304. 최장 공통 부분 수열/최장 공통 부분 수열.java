import java.io.*;

class Solution {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String readString() throws IOException {
        st.nextToken();
        return st.sval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCount = readInt();

        for (int tc = 1; tc <= testCount; tc++) {
            String s1 = readString();
            String s2 = readString();

            int n = s1.length();
            int m = s2.length();

            int[][] dp = new int[n + 1][m + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            bw.append(String.format("#%d %d\n", tc, dp[n][m]));
        }

        bw.flush();
        bw.close();
    }
}