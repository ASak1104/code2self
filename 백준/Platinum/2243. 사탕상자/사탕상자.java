import java.io.*;

public class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int n = readInt();
        SegmentTree st = new SegmentTree();

        while (n-- > 0) {
            int a = readInt();
            int b = readInt();

            if (a == 1) {
                sb.append(st.query(1, 1, 1_000_000, b));
                sb.append('\n');
            } else {
                st.insert(1, 1, 1_000_000, b, readInt());
            }
        }

        System.out.println(sb);
    }
}

class SegmentTree {
    int[] tree = new int[1 << 21];

    void insert(int node, int s, int e, int candy, int count) {
        if (candy < s || e < candy) return;

        if (s == e) {
            tree[node] += count;
            return;
        }

        int left = node << 1;
        int mid = (s + e) >>> 1;

        insert(left, s, mid, candy, count);
        insert(left + 1, mid + 1, e, candy, count);

        tree[node] = tree[left] + tree[left + 1];
    }

    int query(int node, int s, int e, int rank) {
        tree[node]--;

        if (s == e) {
            return s;
        }

        int left = node << 1;
        int mid = (s + e) >>> 1;

        if (tree[left] >= rank) {
            return query(left, s, mid, rank);
        }

        return query(left + 1, mid + 1, e, rank - tree[left]);
    }
}