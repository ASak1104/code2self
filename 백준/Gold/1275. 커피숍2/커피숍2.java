import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static StringTokenizer st;
    static long[] tree;
    static int n, s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = nextInt();
        int q = nextInt();

        st = new StringTokenizer(br.readLine());

        init();

        StringBuilder sb = new StringBuilder();

        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());

            sb.append(query(nextInt(), nextInt())).append('\n');
            update(nextInt(), nextInt());
        }

        System.out.println(sb);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static void init() {
        s = 1;

        while (s < n) s <<= 1;

        tree = new long[s << 1];

        for (int i = s; i < s + n; i++) {
            tree[i] = nextInt();
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