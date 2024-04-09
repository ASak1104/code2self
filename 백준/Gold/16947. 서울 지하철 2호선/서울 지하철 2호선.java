import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();

    static List<Integer>[] edges;
    static StringTokenizer st;
    static boolean[] visits;
    static int[] ids, dists;
    static int n, id, target, targetId;

    public static void main(String[] args) throws IOException {
        init();

        findParents(0, 0);
        findDists(target, 0);

        for (int u = 0; u < n; u++) {
            sb.append(dists[u]).append(' ');
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        edges = new List[n];
        visits = new boolean[n];
        ids = new int[n];
        dists = new int[n];

        for (int u = 0; u < n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            edges[u].add(v);
            edges[v].add(u);
        }
    }

    static void findParents(int u, int p) {
        int uid = ++id;

        ids[u] = uid;

        for (int v : edges[u]) {
            if (v == p) {
                continue;
            }

            if (ids[v] == 0) {
                findParents(v, u);
            }

            ids[u] = Math.min(ids[u], ids[v]);
        }

        if (ids[u] < uid) {
            target = u;
            targetId = ids[u];
        }
    }

    static void findDists(int u, int dist) {
        if (ids[u] == targetId) {
            dist = 0;
        }

        visits[u] = true;
        dists[u] = dist;

        for (int v : edges[u]) {
            if (!visits[v]) {
                findDists(v, dist + 1);
            }
        }
    }

}
