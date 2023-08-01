import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

class Main {

    static double[][] memo = new double[2501][2501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = parseInt(br.readLine());
        int total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stones = new int[m];

        for (int i = 0; i < m; i++) {
            stones[i] = parseInt(st.nextToken());
            total += stones[i];
        }

        int k = parseInt(br.readLine());

        double denom = comb(total, k);
        double numer = 0;

        for (int stone : stones) {
            if (stone >= k) {
                numer += comb(stone, k);
            }
        }

        System.out.printf("%.20f", numer / denom);
    }

    static double comb(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        if (k == 1 || k == n - 1) {
            return n;
        }

        if (memo[n][k] > 0) {
            return memo[n][k];
        }

        double ret = comb(n - 1, k - 1) + comb(n - 1, k);

        memo[n][k] = ret;
        memo[n][n - k] = ret;

        return ret;
    }
}