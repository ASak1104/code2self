import java.io.*;
import java.util.Arrays;

class Main {

    private static final StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    private static final int MAX = (int) 1e9;
    private static final int[][] dp = new int[101][101];

    private static int readInt() throws IOException {
        sttk.nextToken();

        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        int m = readInt();
        int k = readInt();

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        if (k > getDP(n, m)) {
            System.out.println(-1);
            return;
        }

        int a = n;
        int z = m;

        while (a > 0 && z > 0) {
            int count = getDP(a - 1, z);

            if (k <= count) {
                sb.append('a');
                a--;
            } else {
                sb.append('z');
                k -= count;
                z--;
            }
        }

        while (a-- > 0) sb.append('a');

        while (z-- > 0) sb.append('z');

        System.out.println(sb);
    }

    static int getDP(int a, int z) {
        if (a == 0 || z == 0) {
            return 1;
        }

        if (dp[a][z] != -1) {
            return dp[a][z];
        }

        int ret = Math.min(getDP(a - 1, z) + getDP(a, z - 1), MAX);

        dp[a][z] = ret;

        return ret;
    }
}