import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Node[] nodes = new Node[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());

            char nv = st.nextToken().charAt(0);
            char lv = st.nextToken().charAt(0);
            char rv = st.nextToken().charAt(0);

            nodes[nv - 'A'] = new Node(nv, lv, rv);
        }

        nodes[0].preorder();
        bw.newLine();

        nodes[0].inorder();
        bw.newLine();

        nodes[0].postorder();

        bw.flush();
        bw.close();
    }

    static class Node {

        char v;
        char left;
        char right;

        Node(char v, char left, char right) {
            this.v = v;
            this.left = left;
            this.right = right;
        }

        void preorder() throws IOException {
            bw.append(v);

            if (left != '.') {
                nodes[left - 'A'].preorder();
            }

            if (right != '.') {
                nodes[right - 'A'].preorder();
            }
        }

        void inorder() throws IOException {
            if (left != '.') {
                nodes[left - 'A'].inorder();
            }

            bw.append(v);

            if (right != '.') {
                nodes[right - 'A'].inorder();
            }
        }

        void postorder() throws IOException {
            if (left != '.') {
                nodes[left - 'A'].postorder();
            }

            if (right != '.') {
                nodes[right - 'A'].postorder();
            }

            bw.append(v);
        }
    }
}