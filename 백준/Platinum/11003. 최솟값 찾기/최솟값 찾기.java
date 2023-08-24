import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {
    static Node[] seq;
    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        seq = new Node[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            seq[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        Deque<Node> deque = new ArrayDeque<>(L);

        for (int e = 0; e < N; e++) {
            Node next = seq[e];

            while (!deque.isEmpty() && deque.getLast().value > next.value) {
                deque.removeLast();
            }

            deque.addLast(next);

            if (deque.getFirst().index < e - L + 1) {
                deque.removeFirst();
            }

            sb.append(deque.getFirst().value).append(' ');
        }

        System.out.println(sb);
        br.close();
    }
}

class Node {
    int index;
    int value;

    Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}