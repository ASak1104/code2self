import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static final int MOD = 1_000_000_007;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[][] edges = new long[n + 1][n + 1];
        long[][] counts = new long[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u][v] = 1;
            edges[v][u] = 1;
        }

        int d = Integer.parseInt(br.readLine());

        counts[1][1] = 1;

        while (d > 0) {
            if ((d & 1) == 1) {
                counts = mul(counts, edges);
            }

            edges = mul(edges, edges);
            d >>>= 1;
        }

        System.out.println(counts[1][1]);
        br.close();
    }

    private static long[][] mul(long[][] A, long[][] B) {
        long[][] ret = new long[n + 1][n + 1];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                for (int k = 1; k <= n; k++) {
                    ret[r][c] = (ret[r][c] + A[r][k] * B[k][c]) % MOD;
                }
            }
        }

        return ret;
    }
}