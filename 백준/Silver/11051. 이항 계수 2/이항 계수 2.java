import java.io.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    private static final int MOD = 10_007;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int k = readInt();

        int c = 1;

        for (int i = 2; i <= k; i++) {
            c = (c * i) % MOD;
        }

        int a = combi(n, k);
        int b = pow(c, MOD - 2);

        System.out.println((a * b) % MOD);
    }

    // n * n - 1 * ... * n - k + 1
    static int combi(int n, int k) {
        int ret = 1;

        for (int i = n; i > n - k; i--) {
            ret = (ret * i) % MOD;
        }

        return ret;
    }

    static int pow(int x, int y) {
        int ret = 1;

        while (y > 0) {
            if ((y & 1) == 1) {
                ret = (ret * x) % MOD;
            }

            x = (x * x) % MOD;
            y >>>= 1;
        }

        return ret;
    }
}