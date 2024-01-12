import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Main {

    static final int INF = (int) 1e9;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    static Map<Integer, Integer>[] edges;
    static int n, m;

    public static int readInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        edges = new Map[n];

        for (int u = 0; u < n; u++) {
            edges[u] = new HashMap<>();
        }

        for (int i = 0; i < m; i++) {
            int u = readInt() - 1;
            int v = readInt() - 1;
            int w = readInt();
            int prev = edges[u].getOrDefault(v, INF);

            edges[u].put(v, Math.min(w, prev));
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

            for (int v : edges[u].keySet()) {
                int newDist = cost + edges[u].get(v);

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
