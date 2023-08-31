import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    private static int[][] board;
    private static int N, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;

        simulate(0, 0, 0);

        int prev = max;

        max = 0;

        simulate(0, 1, 0);

        System.out.println(prev + max);
    }

    private static void simulate(int r0, int c0, int count) {
        max = Math.max(max, count);

        for (int c = c0; c < N; c += 2) {
            if (board[r0][c] < 1) continue;

            addBishop(r0, c);

            simulate(r0, c + 2, count + 1);

            removeBishop(r0, c);
        }

        int cs = (c0 + 1) % 2;

        for (int r = r0 + 1; r < N; r++) {
            for (int c = cs; c < N; c += 2) {
                if (board[r][c] < 1) continue;

                addBishop(r, c);

                simulate(r, c + 2, count + 1);

                removeBishop(r, c);
            }

            cs = (cs + 1) % 2;
        }
    }

    private static void addBishop(int r0, int c0) {
        int r = r0 + 1;
        int c = c0 + 1;

        while (r >= 0 && r < N && c >= 0 && c < N) {
            board[r][c]--;
            r++;
            c++;
        }

        r = r0 + 1;
        c = c0 - 1;

        while (r >= 0 && r < N && c >= 0 && c < N) {
            board[r][c]--;
            r++;
            c--;
        }
    }

    private static void removeBishop(int r0, int c0) {
        int r = r0 + 1;
        int c = c0 + 1;

        while (r >= 0 && r < N && c >= 0 && c < N) {
            board[r][c]++;
            r++;
            c++;
        }

        r = r0 + 1;
        c = c0 - 1;

        while (r >= 0 && r < N && c >= 0 && c < N) {
            board[r][c]++;
            r++;
            c--;
        }
    }
}