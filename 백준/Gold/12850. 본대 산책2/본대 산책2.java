import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    private static final int MOD = 1_000_000_007;
    private static long[][] counts, edges;
    private static int D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        D = Integer.parseInt(br.readLine());

        init();
    
        while (D > 0) {
            if ((D & 1) == 1) {
                counts = mul(counts, edges);
            }

            edges = mul(edges, edges);
            D >>>= 1;
        }

        System.out.println(counts[0][0]);
        br.close();
    }

    private static void init() {
        counts = new long[8][8];
        edges = new long[8][8];
        
        counts[0][0] = 1;

        edges[0] = new long[] {0, 1, 1, 0, 0, 0, 0, 0};
        edges[1] = new long[] {1, 0, 1, 1, 0, 0, 0, 0};
        edges[2] = new long[] {1, 1, 0, 1, 1, 0, 0, 0};
        edges[3] = new long[] {0, 1, 1, 0, 1, 1, 0, 0};
        edges[4] = new long[] {0, 0, 1, 1, 0, 1, 1, 0};
        edges[5] = new long[] {0, 0, 0, 1, 1, 0, 0, 1};
        edges[6] = new long[] {0, 0, 0, 0, 1, 0, 0, 1};
        edges[7] = new long[] {0, 0, 0, 0, 0, 1, 1, 0};
    }

    private static long[][] mul(long[][] A, long[][] B) {
        long[][] ret = new long[A.length][B[0].length];

        for (int r = 0; r < A.length; r++) {
            for (int c = 0; c < B[0].length; c++) {
                for (int k = 0; k < B.length; k++) {
                    ret[r][c] = (ret[r][c] + A[r][k] * B[k][c]) % MOD;
                }
            }
        }

        return ret;
    }
}