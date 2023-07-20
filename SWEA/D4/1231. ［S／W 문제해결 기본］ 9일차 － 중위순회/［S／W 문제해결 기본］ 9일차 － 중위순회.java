import java.io.*;
import java.util.StringTokenizer;

class Solution {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            MyTree tree = new MyTree(n);

            while (n-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);

                tree.addNode(node, c);
            }

            bw.append(String.format("#%d ", tc));

            tree.travel(1);

            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static class MyTree {
        int n;
        char[] tree;

        MyTree(int n) {
            this.n = n;
            tree = new char[n + 1];
        }

        void addNode(int node, char c) {
			tree[node] = c;
        }

        void travel(int node) throws IOException {
            if (node > n) return;

            travel(node << 1);

            bw.append(tree[node]);

            travel((node << 1) + 1);
        }
    }
}