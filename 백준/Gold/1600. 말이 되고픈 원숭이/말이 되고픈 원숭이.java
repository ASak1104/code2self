import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int[][] stepWeights = new int[][]{
        new int[]{0, 1},
        new int[]{1, 0},
        new int[]{0, -1},
        new int[]{-1, 0},
    };
    private static int[][] jumpWeights = new int[][]{
        new int[]{-2, 1},
        new int[]{-1, 2},
        new int[]{1, 2},
        new int[]{2, 1},
        new int[]{2, -1},
        new int[]{1, -2},
        new int[]{-1, -2},
        new int[]{-2, -1},
    };
    private static int[][] map, visit;
    private static int K, W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visit = new int[H][W];

        for (int r = 0; r < H; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < W; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulate());
        br.close();
    }

    private static int simulate() {
        Deque<int[]> deque = new ArrayDeque<>(W * H);

        deque.add(new int[]{0, 0, 0, 0});
        visit[0][0] = 1;

        while (!deque.isEmpty()) {
            int[] node = deque.removeFirst();
            int ur = node[0];
            int uc = node[1];
            int step = node[2];
            int jump = node[3];

            if (ur == H - 1 && uc == W - 1) {
                return step + jump;
            }

            for (int[] weight : stepWeights) {
                int vr = ur + weight[0];
                int vc = uc + weight[1];

                if (isInvalid(vr, vc, jump)) {
                    continue;
                }

                visit[vr][vc] |= 1 << jump;
                deque.addLast(new int[]{vr, vc, step + 1, jump});
            }

            if (jump >= K) {
                continue;
            }

            for (int[] weight : jumpWeights) {
                int vr = ur + weight[0];
                int vc = uc + weight[1];

                if (isInvalid(vr, vc, jump + 1)) {
                    continue;
                }

                visit[vr][vc] |= 1 << (jump + 1);
                deque.addLast(new int[]{vr, vc, step, jump + 1});
            }
        }

        return -1;
    }

    private static boolean isInvalid(int r, int c, int jump) {
        return r < 0 || r >= H || c < 0 || c >= W || map[r][c] == 1 || ((visit[r][c] & (1 << jump)) == (1 << jump));
    }

}
