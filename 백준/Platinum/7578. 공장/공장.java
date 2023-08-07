import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static StringTokenizer st;
    static Map<Integer, Integer> A;
    static int[] tree;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            A.put(nextInt(), i);
        }

        st = new StringTokenizer(br.readLine());
        tree = new int[n << 2];

        long cross = 0;

        for (int i = 0; i < n; i++) {
            int machine = nextInt();
            int pair = A.get(machine);

            cross += query(0, n - 1, 1, pair + 1, n - 1);
            insert(0, n - 1, 1, pair);
        }

        System.out.println(cross);
        br.close();
    }

    static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    static void insert(int s, int e, int node, int i) {
        tree[node]++;

        if (s == e) return;

        int mid = (s + e) >>> 1;
        int child = node << 1;

        if (i <= mid) {
            insert(s, mid, child, i);
        } else {
            insert(mid + 1, e, child + 1, i);
        }
    }

    static int query(int s, int e, int node, int qs, int qe) {
        if (e < qs || s > qe) return 0;

        if (qs <= s && e <= qe) {
            return tree[node];
        }

        int mid = (s + e) >>> 1;
        int child = node << 1;

        return query(s, mid, child, qs, qe) + query(mid + 1, e, child + 1, qs, qe);
    }
}