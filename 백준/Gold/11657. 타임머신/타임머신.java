import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final int INF = (int) 1e9;
    static StringTokenizer st;
    static List<Node> edges;
    static long[] dists;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = nextInt();
        m = nextInt();

        dists = new long[n + 1];
        edges = new ArrayList<>(m);

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = nextInt();
            int v = nextInt();
            int w = nextInt();

            edges.add(new Node(u, v, w));
        }

        bellmanFord(1);

        StringBuilder sb = new StringBuilder();

        if (hasNegativeCycle(1)) {
            sb.append(-1);
        } else {
            for (int v = 2; v <= n; v++) {
                sb.append(dists[v] == INF ? -1 : dists[v]).append('\n');
            }
        }

        System.out.println(sb);
        br.close();
    }

    static void bellmanFord(int s) {
        Arrays.fill(dists, INF);
        dists[s] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Node e : edges) {
                long dw = dists[e.u] + e.w;

                if (dists[e.u] != INF && dw < dists[e.v]) {
                    dists[e.v] = dw;
                }
            }
        }
    }

    static boolean hasNegativeCycle(int s) {
        for (Node e : edges) {
            long dw = dists[e.u] + e.w;

            if (dists[e.u] != INF && dw < dists[e.v]) {
                return true;
            }
        }

        return false;
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static class Node {
        int u;
        int v;
        int w;

        Node(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}