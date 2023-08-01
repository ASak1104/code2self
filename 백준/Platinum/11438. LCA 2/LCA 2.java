import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer>[] edges;
    static int[][] ancestors;
    static int[] depths;
    static int height;
    static int n;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        setHeight();

        ancestors = new int[n + 1][height + 1];
        depths = new int[n + 1];
        edges = new List[n + 1];

        for (int u = 1; u <= n; u++) {
            edges[u] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int u = readInt();
            int v = readInt();

            edges[u].add(v);
            edges[v].add(u);
        }

        build(0, 1);

        StringBuilder sb = new StringBuilder();
        int m = readInt();

        while (m-- > 0) {
            sb.append(findLCA(readInt(), readInt()));
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void setHeight() {
        height = 0;

        while ((1 << height) < n) height++;
    }

    static int findLCA(int a, int b) {
        if (depths[a] < depths[b]) {
            return findLCA(b, a);
        }

        for (int i = height; i >= 0; i--) {
            if (depths[a] == depths[b]) break;

            int aa = ancestors[a][i];

            if (depths[aa] >= depths[b]) {
                a = aa;
            }
        }

        if (a == b) return a;

        int lca = 1;

        for (int i = height; i >= 0; i--) {
            int aa = ancestors[a][i];
            int ab = ancestors[b][i];

            if (aa == ab) {
                lca = aa;
            } else {
                a = aa;
                b = ab;
            }
        }

        return lca;
    }

    static void build(int p, int u) {
        depths[u] = depths[p] + 1;
        ancestors[u][0] = p;

        for (int i = 1; i <= height; i++) {
            int ancestor = ancestors[u][i - 1];

            ancestors[u][i] = ancestors[ancestor][i - 1];
        }

        for (int v : edges[u]) {
            if (v != p) build(u, v);
        }
    }
}