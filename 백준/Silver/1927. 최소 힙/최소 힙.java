import java.io.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = readInt();
        MinHeap heap = new MinHeap(n);

        while (n-- > 0) {
            int v = readInt();

            if (v > 0) {
                heap.add(v);
            } else {
                bw.append(String.format("%d\n", heap.pop()));
            }
        }

        bw.flush();
        bw.close();
    }
}

class MinHeap {
    int last;
    int[] tree;

    MinHeap(int n) {
        last = 0;
        tree = new int[n << 2];
    }

    void add(int v) {
        int node = ++last;
        int parent = node >>> 1;
        tree[last] = v;

        while (node > 1 && tree[node] < tree[parent]) {
            swapTree(parent, node);

            node = parent;
            parent = node >>> 1;
        }
    }

    int pop() {
        if (last == 0) return 0;

        tree[0] = tree[1];
        tree[1] = tree[last];

        int node = 1;

        while (node < last) {
            int left = node << 1;
            int right = left + 1;
            int next = node;

            if (left < last && tree[node] > tree[left]) {
                next = left;
            }

            if (right < last && tree[next] > tree[right]) {
                next = right;
            }

            if (next == node) break;

            swapTree(node, next);

            node = next;
        }

        last--;

        return tree[0];
    }

    void swapTree(int i, int j) {
        int temp = tree[i];

        tree[i] = tree[j];
        tree[j] = temp;
    }
}