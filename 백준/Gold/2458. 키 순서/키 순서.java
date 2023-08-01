import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {

    static StreamTokenizer sttk = new StreamTokenizer(new InputStreamReader(System.in));
    static List<Integer>[] oriEdges;
    static List<Integer>[] revEdges;
    static int n;

    static int readInt() throws IOException {
        sttk.nextToken();
        return (int) sttk.nval;
    }

    public static void main(String[] args) throws IOException {
        n = readInt();
        oriEdges = new List[n + 1];
        revEdges = new List[n + 1];

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

        boolean[] visit = new boolean[n + 1];
        int res = 0;

        for (int u = 1; u <= n; u++) {
            Arrays.fill(visit, false);

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