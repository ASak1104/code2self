import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static Deque<Integer> deque = new ArrayDeque<>();
    static List<Deque<Integer>> mods = new ArrayList<>();
    static SegmentTree segmentTree;
    static int Q, mod;

    public static void main(String[] args) throws IOException {
        init();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            execute(cmd);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());
        mod = Integer.parseInt(st.nextToken());
        segmentTree = new SegmentTree(mod);

        for (int i = 0; i < mod; i++) {
            Deque<Integer> deque = new ArrayDeque<>();

            deque.add(-1);
            mods.add(deque);
            segmentTree.update(i, -1);
        }
    }

    static void execute(int cmd) throws IOException {
        if (cmd == 1) {
            int r = Integer.parseInt(st.nextToken()) % mod;

            deque.addLast(r);
            mods.get(r).addLast(deque.size());
            segmentTree.update(r, deque.size());

            return;
        }

        if (cmd == 2) {
            if (deque.isEmpty()) {
                return;
            }

            int r = deque.removeLast();

            mods.get(r).removeLast();
            segmentTree.update(r, mods.get(r).getLast());

            return;
        }

        int min = segmentTree.getMin();

        if (min != -1) {
            min = deque.size() - min + 1;
        }

        bw.append(Integer.toString(min));
        bw.newLine();
    }

}

class SegmentTree {

    int cap;
    int[] tree;

    public SegmentTree(int n) {
        cap = 1;

        while (cap < n) {
            cap <<= 1;
        }

        tree = new int[cap << 1];

        Arrays.fill(tree, Integer.MAX_VALUE);
    }

    public void update(int i, int v) {
        tree[cap + i] = v;

        int node = (cap + i) >>> 1;

        while (node > 0) {
            int child = node << 1;

            tree[node] = Math.min(tree[child], tree[child + 1]);
            node >>>= 1;
        }
    }

    public int getMin() {
        return tree[1];
    }

}
