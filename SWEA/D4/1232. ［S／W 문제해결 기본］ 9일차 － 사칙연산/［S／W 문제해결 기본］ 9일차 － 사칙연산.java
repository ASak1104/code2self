import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int tc = 1; tc <= 10; tc++) {
            int n = parseInt(br.readLine());
            Graph graph = new Graph(n);


            while (n-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int node = parseInt(st.nextToken());
                String v = st.nextToken();

                if (isParsable(v)) {
                    graph.addNumer(node, parseInt(v));
                } else {
                    graph.addChar(node, v, parseInt(st.nextToken()), parseInt(st.nextToken()));
                }
            }

            bw.append(String.format("#%d %d\n", tc, (int) graph.calculate(1)));
        }

        bw.flush();
        bw.close();
    }

    static boolean isParsable(String token) {
        return '0' <= token.charAt(0) && token.charAt(0) <= '9';
    }
}

class Graph {
    Node[] tree;

    Graph(int n) {
        tree = new Node[n + 1];
    }

    void addNumer(int i, int v) {
        tree[i] = new Node(v);
    }

    void addChar(int i, String ope, int left, int right) {
        tree[i] = new Node(ope, left, right);
    }

    double calculate(int i) {
        Node node = tree[i];

        if (node.isLeaf) return node.num;

        return node.operate(calculate(node.left), calculate(node.right));
    }

    static class Node {
        String ope;
        int left;
        int right;
        double num;
        boolean isLeaf;

        Node(int num) {
            this.num = num;
            isLeaf = true;
        }

        Node(String ope, int left, int right) {
            this.ope = ope;
            this.left = left;
            this.right = right;
        }

        double operate(double a, double b) {
            switch (ope) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                default:
                    return a / b;
            }
        }
    }
}