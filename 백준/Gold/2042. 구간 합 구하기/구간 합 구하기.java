import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int mk = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        SegmentTree segTree = new SegmentTree(n);

        for (int i = 0; i < n; i++) {
            segTree.update(i, Long.parseLong(br.readLine()));
        }

        while (mk-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                segTree.update((int) b - 1, c);
                continue;
            }

            bw.append(String.format("%d\n", segTree.query((int) b - 1, (int) c - 1)));
        }

        bw.flush();
        bw.close();
    }
}

class SegmentTree {
    long[] tree;
    int cap = 1;

    SegmentTree(int n) {
        while (cap < n) cap <<= 1;

        tree = new long[cap << 1];
    }

    void update(int i, long v) {
        int node = cap + i;
        long w = v - tree[node];

        while (node > 0) {
            tree[node] += w;
            node >>>= 1;
        }
    }

    long query(int b, int c) {
        long res = 0;
        int s = cap + b;
        int e = cap + c;

        while (s < e) {
            if ((s & 1) == 1) res += tree[s++];
            if ((e & 1) == 0) res += tree[e--];

            s >>>= 1;
            e >>>= 1;
        }

        if (s == e) res += tree[s];

        return res;
    }
}