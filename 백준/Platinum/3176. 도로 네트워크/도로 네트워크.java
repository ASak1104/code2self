import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[][] ancestors, minWeights, maxWeights;
    static List<Edge>[] edges;
    static int[] depths;
    static int n, height;
    static int min, max;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        height = 0;

        while ((1 << height) < n) height++;

        ancestors = new int[n + 1][height + 1];
        minWeights = new int[n + 1][height + 1];
        maxWeights = new int[n + 1][height + 1];
        depths = new int[n + 1];
        edges = new List[n + 1];

        for (int u = 1; u <= n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();

            edges[u].add(new Edge(v, w));
            edges[v].add(new Edge(u, w));
        }

        build();

        StringBuilder sb = new StringBuilder();
        int m = readInt();

        while (m-- > 0) {
            findMinMax(readInt(), readInt());

            sb.append(min).append(" ").append(max).append('\n');
        }

        System.out.println(sb);
    }

    static void build() {
        travel(0, 1);

        for (int i = 1; i <= height; i++) {
            for (int u = 1; u <= n; u++) {
                int au = ancestors[u][i - 1];

                if (au == 1) continue;

                ancestors[u][i] = ancestors[au][i - 1];
                minWeights[u][i] = Math.min(minWeights[u][i - 1], minWeights[au][i - 1]);
                maxWeights[u][i] = Math.max(maxWeights[u][i - 1], maxWeights[au][i - 1]);
            }
        }
    }

    static void travel(int p, int u) {
        depths[u] = depths[p] + 1;
        ancestors[u][0] = p;

        for (Edge edge : edges[u]) {
            if (edge.v != p) {
                travel(u, edge.v);

                minWeights[edge.v][0] = edge.w;
                maxWeights[edge.v][0] = edge.w;
            }
        }
    }

    static void findMinMax(int a, int b) {
        if (depths[a] < depths[b]) {
            findMinMax(b, a);
            return;
        }

        min = 1_000_000;
        max = 0;

        for (int i = height; i >= 0; i--) {
            if (depths[a] == depths[b]) break;

            int aa = ancestors[a][i];

            if (depths[aa] >= depths[b]) {
                min = Math.min(min, minWeights[a][i]);
                max = Math.max(max, maxWeights[a][i]);
                a = aa;
            }
        }

        if (a == b) return;

        for (int i = height; i >= 0; i--) {
            int aa = ancestors[a][i];
            int ab = ancestors[b][i];

            if (aa != ab) {
                min = Math.min(min, Math.min(minWeights[a][i], minWeights[b][i]));
                max = Math.max(max, Math.max(maxWeights[a][i], maxWeights[b][i]));
                a = aa;
                b = ab;
            }
        }

        min = Math.min(min, Math.min(minWeights[a][0], minWeights[b][0]));
        max = Math.max(max, Math.max(maxWeights[a][0], maxWeights[b][0]));
    }
}

class Edge {
    int v;
    int w;

    Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }
}