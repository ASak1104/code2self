import java.io.*;
import java.util.*;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static LinkedList<Integer> stack = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static Set<String> set = new LinkedHashSet<>();
    static int n;
    static int m;
    static int[] seq;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        m = readInt();
        seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = readInt();
        }

        Arrays.sort(seq);

        travel(0, 0, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (String s : set) {
            bw.append(s);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    static void travel(int start, int count, int visit) {
        if (count == m) {
            sb.setLength(0);

            for (int e : stack) {
                sb.append(e);
                sb.append(' ');
            }

            set.add(sb.toString());

            return;
        }

        for (int i = start; i < n; i++) {
            int mask = 1 << i;

            if ((visit & mask) == mask) continue;

            stack.addLast(seq[i]);

            travel(i + 1, count + 1, visit | mask);

            stack.removeLast();
        }
    }
}