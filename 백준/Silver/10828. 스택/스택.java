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
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int n = readInt();

        while (n-- > 0) {
            String cmd = readString();

            if (cmd.equals("push")) {
				stack.addLast(readInt());
                continue;
            } else if (cmd.equals("pop")) {
                if (stack.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(stack.removeLast());
                }
            } else if (cmd.equals("size")) {
                sb.append(stack.size());
            } else if (cmd.equals("empty")) {
                sb.append(stack.isEmpty() ? 1 : 0);
            } else {
                if (stack.isEmpty()) {
                    sb.append(-1);
                } else {
                    sb.append(stack.getLast());
                }
            }

            sb.append('\n');
        }

        System.out.println(sb);
    }
}