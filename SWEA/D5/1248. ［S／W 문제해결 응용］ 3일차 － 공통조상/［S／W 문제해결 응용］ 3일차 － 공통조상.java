import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Solution {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCount = readInt();

        for (int tc = 1; tc <= testCount; tc++) {
            int n = readInt();
            int m = readInt();
            int c1 = readInt();
            int c2 = readInt();

            Graph graph = new Graph(n);

            while (m-- > 0) {
                graph.addEdge(readInt(), readInt());
            }

            int lca = graph.findLCA(c1, c2);

            bw.append(String.format("#%d %d %d\n", tc, lca, graph.getSize(lca)));
        }

        bw.flush();
        bw.close();
    }
}

class Graph {
    int n;
    int[] parents;
    List<Integer>[] children;

    Graph(int n) {
        this.n = n;
        parents = new int[n + 1];
        children = new List[n + 1];

        for (int u = 0; u <= n; u++) {
            children[u] = new ArrayList<>();
        }
    }

    void addEdge(int u, int v) {
        parents[v] = u;
        children[u].add(v);
    }

    int findLCA(int c1, int c2) {
        boolean[] visit = new boolean[n + 1];

        while (c1 > 0) {
            visit[c1] = true;
            c1 = parents[c1];
        }

        while (!visit[c2]) {
            c2 = parents[c2];
        }

        return c2;
    }

    int getSize(int u) {
        int size = 1;

        for (int v : children[u]) {
            size += getSize(v);
        }

        return size;
    }
}