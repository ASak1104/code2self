import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    static char[] brackets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        brackets = br.readLine().toCharArray();
        br.close();

        Deque<Character> deque = new ArrayDeque<>();

        int res = 0, offset = 1;

        for (int i = 0; i < brackets.length; i++) {
            char c = brackets[i];

            if (c == '(') {
                deque.addLast(c);
                offset *= 2;
            } else if (c == '[') {
                deque.addLast(c);
                offset *= 3;
            } else if (c == ')') {
                if (deque.isEmpty() || deque.getLast() != '(') {
                    res = 0;
                    break;
                }

                if (brackets[i - 1] == '(') {
                    res += offset;
                }

                deque.removeLast();
                offset /= 2;
            } else {
                if (deque.isEmpty() || deque.getLast() != '[') {
                    res = 0;
                    break;
                }

                if (brackets[i - 1] == '[') {
                    res += offset;
                }

                deque.removeLast();
                offset /= 3;
            }
        }

        if (!deque.isEmpty()) res = 0;

        System.out.println(res);
    }
}