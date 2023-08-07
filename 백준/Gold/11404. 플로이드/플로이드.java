import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;
    static int[][] dists;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dists = new int[n + 1][n + 1];

        for (int u = 1; u <= n; u++) {
            for (int v = 1; v <= n; v++) {
                dists[u][v] = INF;
            }

            dists[u][u] = 0;
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();

            dists[u][v] = Math.min(dists[u][v], w);
        }

        for (int k = 1; k <= n; k++) {
            for (int u = 1; u <= n; u++) {
                for (int v = 1; v <= n; v++) {
                    int ukv = dists[u][k] + dists[k][v];

                    if (ukv < dists[u][v]) {
                        dists[u][v] = ukv;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int u = 1; u <= n; u++) {
            for (int v = 1; v <= n; v++) {
                sb.append(dists[u][v] == INF ? 0 : dists[u][v]).append(' ');
            }

            sb.append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}