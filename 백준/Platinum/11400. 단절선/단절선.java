import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static StringTokenizer st;
    static List<Integer>[] edges;
    static List<Edge> res;
    static int[] ids;
    static int V, E, id = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = nextInt();
        E = nextInt();

        ids = new int[V + 1];
        edges = new List[V + 1];

        for (int u = 1; u <= V; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = nextInt();
            int v = nextInt();

            edges[u].add(v);
            edges[v].add(u);
        }

        res = new ArrayList<>();

        travel(1, 1);

        StringBuilder sb = new StringBuilder();

        res.sort((a, b) -> {
            if (a.u == b.u) return a.v - b.v;

            return a.u - b.u;
        });

        sb.append(res.size()).append('\n');

        for (Edge edge : res) {
            sb.append(edge.u).append(' ').append(edge.v).append('\n');
        }

        System.out.println(sb);
        br.close();
    }

    static int travel(int p, int u) {
        ids[u] = ++id;

        int minId = id;

        for (int v : edges[u]) {
            if (v == p) continue;

            int cid;

            if (ids[v] > 0) {
                cid = ids[v];
            } else {
                cid = travel(u, v);
            }

            if (ids[u] < cid) {
                res.add(new Edge(u, v));
            }

            minId = Math.min(minId, cid);
        }

        ids[u] = minId;

        return minId;
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            if (u < v) {
                this.u = u;
                this.v = v;
            } else {
                this.u = v;
                this.v = u;
            }
        }
    }
}