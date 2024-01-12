import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {

    static final int INF = (int) 1e9;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Edge>[] edges;
    static int n, m;

    public static int readInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        edges = new List[n];

        for (int u = 0; u < n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = readInt() - 1;
            int v = readInt() - 1;
            int w = readInt();

            edges[u].add(new Edge(v, w));
        }

        int src = readInt() - 1;
        int dst = readInt() - 1;

        System.out.println(dijkstra(src, dst));
    }

    private static int dijkstra(int src, int dst) {
        int[] dists = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Arrays.fill(dists, INF);
        dists[src] = 0;
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int u = pq.peek()[0];
            int cost = pq.poll()[1];

            if (u == dst) {
                return cost;
            }

            for (Edge edge : edges[u]) {
                if (dists[edge.node] <= cost + edge.weight) {
                    continue;
                }

                dists[edge.node] = cost + edge.weight;
                pq.add(new int[]{edge.node, cost + edge.weight});
            }
        }

        return dists[dst];
    }

    static class Edge {

        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

    }

}
