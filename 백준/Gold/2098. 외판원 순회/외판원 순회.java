import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;
    static int[][] edges, memo;
    static int n, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        end = (1 << n) - 1;

        edges = new int[n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < n; c++) {
                edges[r][c] = nextInt();
            }
        }

        memo = new int[end + 1][n];

        System.out.println(travel(0, 1));
    }

    static int travel(int u, int visit) {
        if (visit == end) {
            if (edges[u][0] == 0) {
                return INF;
            }

            return edges[u][0];
        }

        if (memo[visit][u] > 0) {
            return memo[visit][u];
        }

        int ret = INF;

        for (int v = 1; v < n; v++) {
            if (edges[u][v] == 0) continue;

            int mask = 1 << v;

            if ((visit & mask) == mask) continue;

            ret = Math.min(ret, edges[u][v] + travel(v, visit | mask));
        }

        memo[visit][u] = ret;

        return ret;
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}