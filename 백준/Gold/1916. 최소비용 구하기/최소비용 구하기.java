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
    static List<Integer>[] edges;
    static int[][] buses;
    static int n, m;

    public static int readInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        buses = new int[n][n];
        edges = new List[n];

        for (int u = 0; u < n; u++) {
            Arrays.fill(buses[u], INF);
            edges[u] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = readInt() - 1;
            int v = readInt() - 1;
            int w = readInt();

            buses[u][v] = Math.min(buses[u][v], w);
        }

        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (buses[u][v] == INF) {
                    continue;
                }

                edges[u].add(v);
            }
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

            for (int v : edges[u]) {
                int newDist = cost + buses[u][v];

                if (dists[v] <= newDist) {
                    continue;
                }

                dists[v] = newDist;
                pq.add(new int[]{v, newDist});
            }
        }

        return dists[dst];
    }

}
