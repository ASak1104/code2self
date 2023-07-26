import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int readInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String readString() throws IOException {
        st.nextToken();
        return st.sval;
    }

    public static void main(String[] args) throws IOException {
        Deque<Integer> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int n = readInt();

        while (n-- > 0) {
            String cmd = readString();

            if (cmd.equals("push")) {
				queue.addLast(readInt());
                continue;
            } else if (cmd.equals("pop")) {
                if (queue.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(queue.removeFirst());
                }
            } else if (cmd.equals("size")) {
                sb.append(queue.size());
            } else if (cmd.equals("empty")) {
                sb.append(queue.isEmpty() ? 1 : 0);
            } else if (cmd.equals("front")) {
                if (queue.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(queue.getFirst());
                }
            } else {
                if (queue.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(queue.getLast());
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}