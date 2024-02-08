import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {

    static final int[] rowWeights = new int[]{1, 0, -1, 0};
    static final int[] colWeights = new int[]{0, 1, 0, -1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] table;
    static int n, m, maxDistance;

    public static void main(String[] args) throws IOException {
        init();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (table[r][c] == 'W') {
                    continue;
                }

                travel(r, c);
            }
        }

        System.out.println(maxDistance);
    }

    private static void travel(int sr, int sc) {
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] visits = new boolean[n][m];

        deque.addLast(new int[]{sr, sc, 0});
        visits[sr][sc] = true;

        while (!deque.isEmpty()) {
            int[] u = deque.removeFirst();

            if (maxDistance < u[2]) {
                maxDistance = u[2];
            }

            for (int i = 0; i < 4; i++) {
                int vr = u[0] + rowWeights[i];
                int vc = u[1] + colWeights[i];

                if (isInvalid(vr, vc) || visits[vr][vc]) {
                    continue;
                }

                deque.addLast(new int[]{vr, vc, u[2] + 1});
                visits[vr][vc] = true;
            }
        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= m || table[r][c] == 'W';
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        table = new char[n][m];

        for (int r = 0; r < n; r++) {
            table[r] = br.readLine().toCharArray();
        }
    }

}
