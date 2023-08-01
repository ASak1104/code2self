import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer>[] edges = new List[501];
    static int[] costs = new int[501];
    static int[] dp = new int[501];
    static int n = 0;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();

        for (int u = 1; u <= n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int u = 1; u <= n; u++) {
            int w = readInt();
            int v = readInt();

            while (v != -1) {
                edges[u].add(v);
                v = readInt();
            }

            costs[u] = w;
        }

        StringBuilder sb = new StringBuilder();

        for (int u = 1; u <= n; u++) {
            sb.append(getDP(u));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static int getDP(int u) {
        if (dp[u] > 0) return dp[u];

        int parent = 0;

        for (int v : edges[u]) {
            parent = Math.max(parent, getDP(v));
        }

        dp[u] = parent + costs[u];

        return dp[u];
    }
}