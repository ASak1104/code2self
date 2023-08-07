import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = nextInt();
        int m = nextInt();

        st = new StringTokenizer(br.readLine());
        int start = nextInt();

        List<Node>[] edges = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();

            edges[u].add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(n, Comparator.comparingInt(a -> a.w));
        int[] dist = new int[n + 1];

        pq.add(new Node(start, 0));
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node u = pq.poll();

            if (u.w != dist[u.v]) continue;

            for (Node edge : edges[u.v]) {
                int dw = u.w + edge.w;

                if (dw < dist[edge.v]) {
                    dist[edge.v] = dw;
                    pq.add(new Node(edge.v, dw));
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int u = 1; u <= n; u++) {
            sb.append(dist[u] == INF ? "INF" : dist[u]).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}