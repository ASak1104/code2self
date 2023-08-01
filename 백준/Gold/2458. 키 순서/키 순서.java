import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer>[] oriEdges = new List[501];
    static List<Integer>[] revEdges = new List[501];
    static int n = 0;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();

        for (int u = 1; u <= n; u++) {
            oriEdges[u] = new ArrayList<>();
            revEdges[u] = new ArrayList<>();
        }

        int m = readInt();

        while (m-- > 0) {
            int u = readInt();
            int v = readInt();

            oriEdges[u].add(v);
            revEdges[v].add(u);
        }

        int res = 0;

        for (int u = 1; u <= n; u++) {
            boolean[] visit = new boolean[n + 1];

            int oriCnt = travel(u, oriEdges, visit);
            int revCnt = travel(u, revEdges, visit);

            if (oriCnt + revCnt == n + 1) res++;
        }

        System.out.println(res);
    }

    static int travel(int u, List<Integer>[] edges, boolean[] visit) {
        visit[u] = true;

        int count = 1;

        for (int v : edges[u]) {
            if (visit[v]) continue;

            count += travel(v, edges, visit);
        }

        return count;
    }
}