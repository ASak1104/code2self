import java.io.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static int[] parents;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        int m = readInt();
        parents = new int[n + 1];

        for (int u = 1; u <= n; u++) {
            parents[u] = u;
        }

        while (m-- > 0) {
            int cmd = readInt();
            int a = readInt();
            int b = readInt();

            if (cmd == 0) {
                merge(a, b);
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
        if (node == parents[node]) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    static void merge(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parents[b] = a;
    }
}