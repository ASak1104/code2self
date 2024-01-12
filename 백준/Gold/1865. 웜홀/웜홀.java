import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Main {

    static final int INF = (int) 1e9;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[][] dists;
    static int TC, N, M, W;

    public static int readInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        TC = readInt();

        while (TC-- > 0) {
            init();
            sb.append(floydWarshall());
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        N = readInt();
        M = readInt();
        W = readInt();
        dists = new int[N][N];

        for (int u = 0; u < N; u++) {
            Arrays.fill(dists[u], INF);
            dists[u][u] = 0;
        }

        while (M-- > 0) {
            int u = readInt() - 1;
            int v = readInt() - 1;
            int w = readInt();

            dists[u][v] = Math.min(dists[u][v], w);
            dists[v][u] = dists[u][v];
        }

        while (W-- > 0) {
            int u = readInt() - 1;
            int v = readInt() - 1;
            int w = -readInt();

            dists[u][v] = Math.min(dists[u][v], w);
        }

    }

    static String floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int u = 0; u < N; u++) {
                for (int v = 0; v < N; v++) {
                    dists[u][v] = Math.min(dists[u][v], dists[u][k] + dists[k][v]);
                }
            }
        }

        for (int u = 0; u < N; u++) {
            if (dists[u][u] < 0) {
                return "YES\n";
            }
        }

        return "NO\n";
    }

}
