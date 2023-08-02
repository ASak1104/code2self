import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        List<Edge>[] edges = new List[n + 1];

        for (int u = 1; u <= n; u++) {
            edges[u] = new ArrayList<>();
        }

        while (m-- > 0) {
            int u = readInt();
            int v = readInt();
            int w = readInt();

            edges[u].add(new Edge(v, w));
            edges[v].add(new Edge(u, w));
        }

        int vertices = 0;
        int totalWeight = 0;
        boolean[] visit = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge u = pq.poll();

            if (visit[u.v]) continue;

            visit[u.v] = true;
            vertices += 1;
            totalWeight += u.w;

            if (vertices == n) break;

            for (Edge edge : edges[u.v]) {
                if (visit[edge.v]) continue;

                pq.add(edge);
            }
        }

        System.out.println(totalWeight);
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