import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[] parents;
    static int n;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        int m = readInt();
        parents = new int[n + 1];

        for (int u = 1; u <= n; u++) {
            parents[u] = u;
        }

        List<Edge> edges = new ArrayList<>(m);

        while (m-- > 0) {
            edges.add(new Edge(readInt(), readInt(), readInt()));
        }

        edges.sort(Comparator.comparingInt(a -> a.w));

        int edgeCount = 0;
        int totalWeight = 0;

        for (Edge edge : edges) {
            if (edgeCount == n - 1) break;

            if (find(edge.u) == find(edge.v)) continue;

            merge(edge.u, edge.v);

            totalWeight += edge.w;
            edgeCount++;
        }

        System.out.println(totalWeight);
    }

    static int find(int u) {
        if (u == parents[u]) {
            return u;
        }

        return parents[u] = find(parents[u]);
    }

    static void merge(int u, int v) {
        u = find(u);
        v = find(v);

        parents[v] = u;
    }
}

class Edge {
    int u;
    int v;
    int w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}