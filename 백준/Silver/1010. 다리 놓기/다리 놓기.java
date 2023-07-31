import java.io.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    private static final long[][] memo = new long[30][30];

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int t = readInt();

        while (t-- > 0) {
            int n = readInt();
            int m = readInt();

            sb.append(comb(m, n));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static long comb(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        if (k == 1 || k == n - 1) {
            return n;
        }

        if (memo[n][k] != 0) {
            return memo[n][k];
        }

        long ret = comb(n - 1, k - 1) + comb(n - 1, k);

        memo[n][k] = ret;
        memo[n][n - k] = ret;

        return ret;
    }
}