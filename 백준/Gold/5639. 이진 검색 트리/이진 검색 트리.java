import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BST bst = new BST();

        int token = st.nextToken();

        while (token != StreamTokenizer.TT_EOF) {
            int v = (int) st.nval;

            bst.add(v);

            token = st.nextToken();
        }

        bw.append(bst.travel());
        bw.flush();
        bw.close();
    }
}

class BST {

    Node root = new Node(0);

    void add(int v) {
        Node node = root;

        while (true) {
            if (v < node.v) {
                if (node.left == null) {
                    node.left = new Node(v);
                    return;
                }

                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new Node(v);
                    return;
                }

                node = node.right;
            }
        }
    }

    String travel() {
        StringBuilder sb = new StringBuilder();

        travel(root.right, sb);

        return sb.toString();
    }

    void travel(Node node, StringBuilder sb) {
        if (node == null) return;

        travel(node.left, sb);
        travel(node.right, sb);

        sb.append(node.v);
        sb.append('\n');
    }

    private static class Node {
        int v;
        Node left = null;
        Node right = null;

        Node(int v) {
            this.v = v;
        }
    }
}