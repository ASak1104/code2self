import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;
    static List<Node>[] edges;
    static boolean[][] excepts;
    static List<Integer>[] paths;
    static PriorityQueue<Node> pq;
    static int[] dists;
    static int n, m, s, e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = nextInt();
            m = nextInt();

            if (n == 0 && m == 0) break;

            st = new StringTokenizer(br.readLine());
            s = nextInt();
            e = nextInt();

            edges = new List[n];
            paths = new List[n];
            excepts = new boolean[n][n];

            for (int u = 0; u < n; u++) {
                edges[u] = new ArrayList<>();
                paths[u] = new ArrayList<>();
            }

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = nextInt();
                int v = nextInt();
                int w = nextInt();

                edges[u].add(new Node(v, w));
            }

            dijkstra();

            removeShortestPath(e, s);

            sb.append(dijkstra()).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static void removeShortestPath(int u, int d) {
        if (u == d) return;

        for (int v : paths[u]) {
            if (!excepts[v][u]) {
                excepts[v][u] = true;
                removeShortestPath(v, d);
            }
        }
    }

    static int dijkstra() {
        pq = new PriorityQueue<>(n, Comparator.comparingInt(a -> a.w));
        dists = new int[n];

        pq.add(new Node(s, 0));
        Arrays.fill(dists, INF);
        dists[s] = 0;

        while (!pq.isEmpty()) {
            Node u = pq.poll();

            if (u.w < dists[u.v]) continue;

            for (Node e : edges[u.v]) {
                if (excepts[u.v][e.v]) continue;

                int dw = u.w + e.w;

                if (dw < dists[e.v]) {
                    dists[e.v] = dw;
                    pq.add(new Node(e.v, dw));
                    paths[e.v] = new ArrayList<>();
                }

                if (dw == dists[e.v]) {
                    paths[e.v].add(u.v);
                }
            }
        }

        return dists[e] == INF ? -1 : dists[e];
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