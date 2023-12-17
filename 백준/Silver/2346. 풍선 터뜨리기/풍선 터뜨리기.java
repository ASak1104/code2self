import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Deque<Node> deque = new ArrayDeque<>(1_000);
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            Node node = new Node(i, Integer.parseInt(st.nextToken()));

            deque.addLast(node);
        }

        System.out.println(simulate());
        br.close();
    }

    static String simulate() {
        StringBuilder sb = new StringBuilder(n);

        while (!deque.isEmpty()) {
            Node node = deque.removeFirst();

            sb.append(node.index).append(' ');
            rotate(node.move);
        }

        return sb.toString();
    }

    static void rotate(int move) {
        for (int i = 0; i > move && !deque.isEmpty(); i--) {
            deque.addFirst(deque.removeLast());
        }

        for (int i = 1; i < move && !deque.isEmpty(); i++) {
            deque.addLast(deque.removeFirst());
        }
    }

    static class Node {

        int index;
        int move;

        public Node(int index, int move) {
            this.index = index;
            this.move = move;
        }
    }
}
