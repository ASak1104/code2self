import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
    static StringTokenizer st;
    static List<Integer>[] edges;
    static TreeSet<Integer> res = new TreeSet<>();
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

        for (int u = 1; u <= V; u++) {
            if (ids[u] == 0) {
                travel(u, u);
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(res.size()).append('\n');

        for (int u : res) {
            sb.append(u).append(' ');
        }

        System.out.println(sb);
        br.close();
    }

    static int travel(int p, int u) {
        ids[u] = ++id;

        int minId = id;
        int count = 0;

        for (int v : edges[u]) {
            if (v == p) continue;

            if (ids[v] > 0) {
                minId = Math.min(minId, ids[v]);
                continue;
            }

            int cid = travel(u, v);

            if (u != p && ids[u] <= cid) {
                res.add(u);
            }

            count++;
            minId = Math.min(minId, cid);
        }

        if (u == p && count > 1) {
            res.add(u);
        }

        return minId;
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }
}