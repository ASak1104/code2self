import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Main {
    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static long[] tree;
    static int n, s;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        int q = readInt();

        init();

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            sb.append(query(readInt(), readInt())).append('\n');
            update(readInt(), readInt());
        }

        System.out.println(sb);
    }

    static void init() throws IOException {
        s = 1;

        while (s < n) s <<= 1;

        tree = new long[s << 1];

        for (int i = s; i < s + n; i++) {
            tree[i] = readInt();
        }

        for (int i = s - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[(i << 1) + 1];
        }
    }

    static void update(int idx, int v) {
        int node = s + idx - 1;
        long diff = v - tree[node];

        while (node > 0) {
            tree[node] += diff;
            node >>>= 1;
        }
    }

    static long query(int qs, int qe) {
        if (qs > qe) {
            return query(qe, qs);
        }

        qs += s - 1;
        qe += s - 1;

        long ret = 0;

        while (qs <= qe) {
            if ((qs & 1) == 1) ret += tree[qs++];

            if ((qe & 1) == 0) ret += tree[qe--];

            qs >>>= 1;
            qe >>>= 1;
        }

        return ret;
    }
}