import java.io.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[] parents;
    static int[] heights;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        int m = readInt();
        parents = new int[n + 1];
        heights = new int[n + 1];

        for (int u = 1; u <= n; u++) {
            parents[u] = u;
            heights[u] = 1;
        }

        while (m-- > 0) {
            int cmd = readInt();
            int a = readInt();
            int b = readInt();

            if (cmd == 0) {
                merge(find(a), find(b));
                continue;
            }

            if (find(a) == find(b)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    static int find(int node) {
        while (node != parents[node]) {
            node = parents[node];
        }

        return node;
    }

    static void merge(int a, int b) {
        if (heights[a] < heights[b]) {
            merge(b, a);
            return;
        }

        parents[b] = a;

        if (heights[a] == heights[b]) {
            heights[a]++;
        }
    }
}