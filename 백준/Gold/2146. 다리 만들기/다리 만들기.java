import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] islands, visits;
    static int[] rowWeights = new int[]{0, 1, 0, -1};
    static int[] colWeights = new int[]{-1, 0, 1, 0};
    static int n, answer = 200;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        islands = new boolean[n][n];
        visits = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < n; c++) {
                islands[r][c] = st.nextToken().equals("1");
            }
        }

        findShortestBridge();

        System.out.println(answer);
    }

    static void findShortestBridge() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (!islands[r][c] || visits[r][c]) {
                    continue;
                }

                findBridge(r, c);
            }
        }
    }

    static void findBridge(int sr, int sc) {
        Deque<int[]> dq = new ArrayDeque<>();
        Deque<int[]> seas = new ArrayDeque<>();

        dq.offerLast(new int[]{sr, sc});
        visits[sr][sc] = true;

        while (!dq.isEmpty()) {
            int r = dq.peekFirst()[0];
            int c = dq.pollFirst()[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + rowWeights[i];
                int nc = c + colWeights[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || visits[nr][nc]) {
                    continue;
                }

                visits[nr][nc] = true;

                if (islands[nr][nc]) {
                    dq.offerLast(new int[]{nr, nc});
                } else {
                    seas.offerLast(new int[]{nr, nc});
                }
            }
        }

        while (!seas.isEmpty()) {
            int r = seas.peekFirst()[0];
            int c = seas.pollFirst()[1];

            answer = Math.min(answer, travel(r, c));
        }
    }

    static int travel(int sr, int sc) {
        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] localVisits = new boolean[n][n];

        dq.offerLast(new int[]{sr, sc, 1});
        localVisits[sr][sc] = true;

        while (!dq.isEmpty()) {
            int r = dq.peekFirst()[0];
            int c = dq.peekFirst()[1];
            int w = dq.pollFirst()[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + rowWeights[i];
                int nc = c + colWeights[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || localVisits[nr][nc]) {
                    continue;
                }

                localVisits[nr][nc] = true;

                if (!islands[nr][nc]) {
                    dq.offerLast(new int[]{nr, nc, w + 1});
                    continue;
                }

                if (!visits[nr][nc]) {
                    return w;
                }
            }
        }

        return answer;
    }

}
