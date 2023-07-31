import java.io.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[][] memo = new int[1001][1001];

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int k = readInt();

        System.out.println(comb(n, k));
    }

    static int comb(int n, int k) {
        if (k == 0 || n == k) return 1;

        if (k == 1 || k == n - 1) {
            return n;
        }

        if (memo[n][k] != 0) {
            return memo[n][k];
        }

        int ret = (comb(n - 1, k - 1) + comb(n - 1, k)) % 10_007;

        memo[n][k] = ret;
        memo[n][n - k] = ret;

        return ret;
    }
}