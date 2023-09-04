import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    private static final int MOD = 1_000_000_007;
    private static int[][] dp;
    private static Edge[] edges;
    private static int D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        D = Integer.parseInt(br.readLine());

        init();

        for (int d = 1; d <= D; d++) {
            for (Edge edge : edges) {
                dp[d][edge.u] = (dp[d][edge.u] + dp[d - 1][edge.v]) % MOD;
                dp[d][edge.v] = (dp[d][edge.v] + dp[d - 1][edge.u]) % MOD;
            }
        }

        System.out.println(dp[D][0]);
        br.close();
    }

    private static void init() {
        edges = new Edge[12];
        dp = new int[D + 1][8];

        dp[0][0] = 1;

        edges[0] = new Edge(0, 1);
        edges[1] = new Edge(0, 2);
        edges[2] = new Edge(1, 2);
        edges[3] = new Edge(1, 3);
        edges[4] = new Edge(2, 3);
        edges[5] = new Edge(2, 4);
        edges[6] = new Edge(3, 4);
        edges[7] = new Edge(3, 5);
        edges[8] = new Edge(4, 5);
        edges[9] = new Edge(4, 6);
        edges[10] = new Edge(5, 7);
        edges[11] = new Edge(6, 7);
    }
}

class Edge {
    int u;
    int v;

    Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}