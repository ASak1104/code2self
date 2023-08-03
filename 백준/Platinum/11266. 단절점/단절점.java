import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer> points = new ArrayList<>(10_000);
    static List<Integer>[] edges;
    static int[] ids;
    static int V, E, root, id = 0;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        V = readInt();
        E = readInt();

        ids = new int[V + 1];
        edges = new List[V + 1];

        for (int u = 1; u <= V; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int u = readInt();
            int v = readInt();

            edges[u].add(v);
            edges[v].add(u);
        }

        for (int u = 1; u <= V; u++) {
            root = u;

            travel(root, root);
        }

        points.sort(Comparator.naturalOrder());

        StringBuilder sb = new StringBuilder();

        sb.append(points.size()).append('\n');

        for (int p : points) {
            sb.append(p).append(' ');
        }

        System.out.println(sb);
    }

    static int travel(int p, int u) {
        ids[u] = ++id;

        int minId = id;
        int count = 0;
        boolean isAdd = false;

        for (int v : edges[u]) {
            if (v == p) continue;

            if (ids[v] > 0) {
                minId = Math.min(minId, ids[v]);
                continue;
            }

            int vid = travel(u, v);

            if (!isAdd && u != root && ids[u] <= vid) {
                points.add(u);
                isAdd = true;
            }

            minId = Math.min(minId, vid);
            count++;
        }

        if (u == root && count > 1) {
            points.add(u);
        }

        return minId;
    }
}