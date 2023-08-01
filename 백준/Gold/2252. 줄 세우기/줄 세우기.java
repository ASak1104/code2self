import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer>[] edges;
    static int[] inDegrees;
    static int n;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        int m = readInt();

        edges = new List[n + 1];
        inDegrees = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            int u = readInt();
            int v = readInt();

            edges[u].add(v);
            inDegrees[v]++;
        }

        StringBuilder sb = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int u = 1; u <= n; u++) {
            if (inDegrees[u] == 0) {
                deque.addLast(u);
            }
        }

        while (!deque.isEmpty()) {
            int u = deque.removeFirst();

            sb.append(u);
            sb.append(' ');

            for (int v : edges[u]) {
                if (--inDegrees[v] == 0) {
                    deque.addLast(v);
                }
            }
        }

        System.out.println(sb);
    }
}