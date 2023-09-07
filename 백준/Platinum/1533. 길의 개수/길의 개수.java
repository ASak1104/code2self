import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static final int MOD = 1_000_003;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;
        int t = Integer.parseInt(st.nextToken());

        N = n * 5;

        long[][] edges = new long[N][N];
        long[][] counts = new long[N][N];

        for (int r = 0; r < n; r++) {
            String line = br.readLine();

            for (int j = 0; j < n; j++) {
                int w = line.charAt(j) - '0';

                if (w == 0) continue;

                int c = j + (w - 1) * n;

                edges[r][c] = 1;
            }
        }

        for (int r = n; r < N; r++) {
            edges[r][r - n] = 1;
        }

        counts[s][s] = 1;

        while (t > 0) {
            if ((t & 1) == 1) {
                counts = mul(counts, edges);
            }

            edges = mul(edges, edges);
            t >>>= 1;
        }

        System.out.println(counts[s][e]);
        br.close();
    }

    private static long[][] mul(long[][] A, long[][] B) {
        long[][] ret = new long[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int k = 0; k < N; k++) {
                    ret[r][c] = (ret[r][c] + A[r][k] * B[k][c]) % MOD;
                }
            }
        }

        return ret;
    }
}